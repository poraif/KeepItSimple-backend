package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.dto.requestbody.term.AddTermReqBody;
import ie.por.keepitsimple.dto.requestbody.termcollection.AddTermCollectionReqBody;
import ie.por.keepitsimple.dto.responsebody.termcollection.TermCollectionResponseBody;
import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.model.TermCollection;
import ie.por.keepitsimple.service.TermCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/collections")
public class TermCollectionController {

    @Autowired
    TermCollectionService termCollectionService;


    @PostMapping(value="/add")
    public void addCollection(@RequestBody AddTermCollectionReqBody requestBody) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        termCollectionService.addCollection(username, requestBody);
    }

    @PutMapping(value="/{collectionName}/update")
    public void updateCollectionDetails(@RequestBody AddTermCollectionReqBody requestBody, @PathVariable String collectionName) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        termCollectionService.updateCollectionDetails(username, requestBody, collectionName);
    }

    @PutMapping(value="/{collectionName}/addterm")
    public void addTermToCollection(@PathVariable String collectionName, @RequestParam String termName) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        termCollectionService.addTermToCollection(username, collectionName, termName);
    }

    @PutMapping(value="/{collectionName}/removeterm")
    public void removeTermFromCollection(@PathVariable String collectionName, @RequestParam String termName) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        termCollectionService.removeTermFromCollection(username, collectionName, termName);
    }

    @GetMapping(value="/list")
    public List<TermCollectionResponseBody> getUserCollections() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return termCollectionService.getUserCollections(username);
    }

    @GetMapping(value="/{collectionName}/termlist")
    public Set<AddTermReqBody> getCollectionTerms(@PathVariable String collectionName) {
        return termCollectionService.getCollectionTerms(collectionName);
    }

    @DeleteMapping(value="/{collectionName}/delete")
    public void deleteCollection(@PathVariable String collectionName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
         termCollectionService.deleteCollection(username, collectionName);
    }
}
