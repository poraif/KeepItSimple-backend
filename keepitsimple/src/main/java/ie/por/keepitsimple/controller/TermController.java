package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.requestbody.term.AddTermReqBody;
import ie.por.keepitsimple.requestbody.termversion.AddTermVersionReqBody;
import ie.por.keepitsimple.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/term")
public class TermController {

    @Autowired
    private TermService termService;

    @PostMapping(value="/term")
    public void addTerm(@ModelAttribute AddTermReqBody requestBody) {
        termService.add(requestBody);
    }


}

