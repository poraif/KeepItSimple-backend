package ie.por.keepitsimple.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    @GetMapping("generate-term")
    public String generateTerm(@RequestParam String term) {
        return aiService.generateTerm(term);
    }

    @GetMapping("check-term")
    public boolean checkTerm(@RequestParam String term) {
        return aiService.checkTerm(term);
    }

    @GetMapping("generate-term-category")
    public String generateTermCategory(@RequestParam String term) {
        return aiService.generateTermCategory(term);
    }

}
