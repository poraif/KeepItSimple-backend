package ie.por.keepitsimple.ai;

import ie.por.keepitsimple.model.TermVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    @GetMapping("/generate-term")
    public TermVersion generateTermVersion(@RequestParam String term) {
        return aiService.generateTermVersion(term);
    }

    @GetMapping("/check-term")
    public boolean checkTerm(@RequestParam String term) {
        return aiService.checkTerm(term);
    }

    @GetMapping("/generate-term-category")
    public String generateTermCategory(@RequestParam String term) {
        return aiService.generateTermCategory(term);
    }

}
