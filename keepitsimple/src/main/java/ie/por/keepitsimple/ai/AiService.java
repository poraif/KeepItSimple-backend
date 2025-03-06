package ie.por.keepitsimple.ai;

import ie.por.keepitsimple.requestbody.termversion.AddTermVersionReqBody;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class AiService {

    private AddTermVersionReqBody addTermVersionReqBody;

    private ChatClient chatClient;

    public AiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Value("classpath:/prompts/generateTermPrompt")
    private Resource generatePrompt;

    @Value("classpath:/prompts/checkTermPrompt")
    private Resource checkPrompt;

    public AddTermVersionReqBody generateTerm(String term) {
        PromptTemplate promptTemplate = new PromptTemplate(generatePrompt);

        Prompt prompt = promptTemplate.create(Map.of("term", term));
        return this.chatClient.prompt(prompt)
                .call()
                .entity(AddTermVersionReqBody.class);
    }

    public boolean checkTerm(String termName) {
        return true;
    }
}
