package ie.por.keepitsimple.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.repository.TermRepository;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.ai.mistralai.api.MistralAiApi;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.stringtemplate.v4.ST;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class AiService {

    private final MistralAiChatModel chatModel;

    @Autowired
    TermRepository termRepository;

    @Autowired
    private final ObjectMapper objectMapper;

    public AiService(@Value("${spring.ai.mistralai.api-key}") String apiKey, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;


        MistralAiChatOptions options = new MistralAiChatOptions();
        options.setModel("open-mistral-nemo");
        options.setMaxTokens(500);
        options.setTemperature(0.2);

        MistralAiApi mistralAiApi = new MistralAiApi(apiKey);

        this.chatModel = new MistralAiChatModel(mistralAiApi,
                MistralAiChatOptions.fromOptions(options),
                ToolCallingManager.builder().build(),
                RetryTemplate.defaultInstance(),
                ObservationRegistry.create());
    }




    @Value("classpath:/prompts/generateTermPrompt.st")
    private Resource generatePrompt;

    @Value("classpath:/prompts/checkTermPrompt.st")
    private Resource checkPrompt;

    @Value("classpath:/prompts/generateCategoryPrompt.st")
    private Resource generateCategoryPrompt;

    public TermVersion generateTermVersion(String term) {
        PromptTemplate promptTemplate = new PromptTemplate(generatePrompt);
        Prompt prompt = promptTemplate.create(Map.of("term", term));
        ChatResponse response = chatModel.call(prompt);
        String result = response.getResult().getOutput().getText();
        System.out.println(result);
        try {
            return objectMapper.readValue(result, TermVersion.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkTerm(String term) {
        PromptTemplate promptTemplate = new PromptTemplate(checkPrompt);
        Prompt prompt = promptTemplate.create(Map.of("term", term));
        String response = chatModel.call(prompt).getResult().getOutput().getText();
        System.out.println("response: " + response);
        if (response.equals("{\"isTerm\": true}")) {
            return true;
        }
        return false;
    }

    public String generateTermCategory(String term) {
        PromptTemplate promptTemplate = new PromptTemplate(generateCategoryPrompt);
        Prompt prompt = promptTemplate.create(Map.of("term", term));
        String response = chatModel.call(prompt).getResult().getOutput().getText();
        System.out.println("response: " + response);
        return response;
    }
}


