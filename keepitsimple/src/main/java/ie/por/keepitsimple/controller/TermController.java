package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.Term;
import ie.por.keepitsimple.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TermController {

    @Autowired
    private TermService termService;

@GetMapping("/")
public List<Term> getAllDefinitions() {
    return termService.getDefinitions();
}

@GetMapping("/peter")
    public String getPeter() {
        return "Petaaa";
    }


@GetMapping("/emily")
public String doEmily() {
    return "emily";
}
}

