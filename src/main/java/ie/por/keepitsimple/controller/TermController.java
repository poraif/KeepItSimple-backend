package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.dto.requestbody.term.AddTermAndVersionReqBody;
import ie.por.keepitsimple.dto.requestbody.termversion.AddTermVersionReqBody;
import ie.por.keepitsimple.dto.responsebody.term.TermAndCurrentVersion;
import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.dto.requestbody.term.AddTermReqBody;
import ie.por.keepitsimple.service.TermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    @GetMapping(value="/search")
    public ResponseEntity<TermAndCurrentVersion> searchTerm(@RequestParam String term) {
        log.info("Received search request for term: {}", term);
        try {
            Optional<TermAndCurrentVersion> result = termService.searchTerm(term);
            if (result.isPresent()) {
                log.info("Search successful for term: {}", term);
                return ResponseEntity.ok(result.get());
            } else {
                log.error("Term not found: {}", term);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Error during search for term: {}", term, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value="/terms")
    public List<String> getAllTermNames() {
        return termService.getAllTermNames();
    }




}

