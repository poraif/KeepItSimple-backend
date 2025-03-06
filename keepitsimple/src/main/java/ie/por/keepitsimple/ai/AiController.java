package ie.por.keepitsimple.ai;

import ie.por.keepitsimple.requestbody.termversion.AddTermVersionReqBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    @Autowired
    private AiService aiService;

    @GetMapping("generate-term")
    public AddTermVersionReqBody generateTerm(@RequestParam String termName) {
        return aiService.generateTerm(termName);
    }

    @GetMapping("check-term")
    public boolean checkTerm(@RequestParam String termName) {
        return aiService.checkTerm(termName);
    }

}
