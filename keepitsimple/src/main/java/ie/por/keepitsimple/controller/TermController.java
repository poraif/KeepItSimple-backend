package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.dto.requestbody.term.AddTermAndVersionReqBody;
import ie.por.keepitsimple.dto.requestbody.termversion.AddTermVersionReqBody;
import ie.por.keepitsimple.dto.responsebody.term.TermAndCurrentVersion;
import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.dto.requestbody.term.AddTermReqBody;
import ie.por.keepitsimple.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/term")
public class TermController {

    @Autowired
    private TermService termService;

    @PostMapping(value="/add")
    public void addTerm(@RequestBody AddTermReqBody requestBody) {
        termService.add(requestBody);
    }

    @PostMapping(value="/addtermandversion")
    public void addTermAndVersion(@RequestBody AddTermAndVersionReqBody requestBody) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        termService.addTermAndVersion(requestBody, username);
    }



    @GetMapping(value="/{id}")
    public Term getTerm(@PathVariable Long id) {
        return termService.findTermById(id);
    }

    @GetMapping(value="/search/")
    public Optional<TermAndCurrentVersion> searchTerm(@RequestParam String term) {
        return termService.searchTerm(term);
    }

    @GetMapping(value="/terms")
    public List<String> getAllTermNames() {
        return termService.getAllTermNames();
    }




}

