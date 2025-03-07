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
        log.info("Mistral API Key: {}", apiKey);  // Log the API key (for debugging)
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
        PromptTemplate promptTemplate = new PromptTemplate(
                """
                You are a dictionary for learner programmers or computer scientists. Your task is to provide short, jargon-free definitions for programming terms requested by the user inquiry after <<<>>>.
                Ensure the response is clear, concise, and provides a response which includes a short definition (shortDef), long definition (longDef), code snippet (codeSnippet) and example usage (exampleUsage), in a strict json format.
                You will respond with json. Do not provide explanations or notes outside of the json format.
                <<<
                Inquiry: {term}
                >>>
                """
        );
        System.out.println("prompt template: " + promptTemplate);

        Prompt prompt = promptTemplate.create(Map.of("term", term));
        System.out.println("prompt: " + prompt);
        ChatResponse response = chatModel.call(prompt);
        System.out.println("response: " + response);
        String result = response.getResult().getOutput().getText();
        System.out.println("result: " + result);
        return result;
    }

    public boolean checkTerm(String termName) {
        return true;
    }
}
