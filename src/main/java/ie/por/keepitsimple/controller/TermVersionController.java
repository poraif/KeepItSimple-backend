package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.dto.requestbody.termversion.AddTermVersionReqBody;
import ie.por.keepitsimple.service.TermVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/term/{name}")
public class TermVersionController {

    @Autowired
    private TermVersionService termVersionService;

    @PostMapping(value="/termversions")
    public void addTermVersion(@RequestBody AddTermVersionReqBody requestBody, @PathVariable String name) {
        termVersionService.add(requestBody, name);
    }

    @PutMapping(value="/termversion/{id}/edit")
    public void updateTermVersion(@RequestBody AddTermVersionReqBody requestBody, @PathVariable Long id, @PathVariable String name) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        termVersionService.updateTermVersion(requestBody, username, name, id);
    }

    @DeleteMapping(value="/termversion/{id}/delete")
    public void deleteTermVersion(@PathVariable String name, @PathVariable Long id) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        termVersionService.deleteTermVersion(id, username, name);
    }



}
