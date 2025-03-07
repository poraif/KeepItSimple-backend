package ie.por.keepitsimple.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.ai.mistralai.api.MistralAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;

import java.util.Map;

@Slf4j
@Service
public class AiService {

    private final MistralAiChatModel chatModel;

    public AiService(@Value("${spring.ai.mistralai.api-key}") String apiKey) {

        MistralAiApi mistralAiApi = new MistralAiApi(apiKey);
        this.chatModel = new MistralAiChatModel(mistralAiApi, MistralAiChatOptions.builder()
                .model(MistralAiApi.ChatModel.OPEN_MISTRAL_NEMO.getValue())
                .temperature(0.2)
                .maxTokens(500)
                .build());
    }

    @Value("classpath:/prompts/generateTermPrompt.st")
    private Resource generatePrompt;

    @Value("classpath:/prompts/checkTermPrompt.st")
    private Resource checkPrompt;

    public String generateTerm(String term) {
        PromptTemplate promptTemplate = new PromptTemplate(generatePrompt);
        Prompt prompt = promptTemplate.create(Map.of("term", term));
        ChatResponse response = chatModel.call(prompt);
        String result = response.getResult().getOutput().getText();
        System.out.println(result);
        return result;
    }

    public boolean checkTerm(String term) {
        PromptTemplate promptTemplate = new PromptTemplate(checkPrompt);
        Prompt prompt = promptTemplate.create(Map.of("term", term));
        String response = chatModel.call(prompt).getResult().getOutput().getText();
        System.out.println("response: " + response);
        return response.equals("{\"isTerm\": true}");
    }
}
