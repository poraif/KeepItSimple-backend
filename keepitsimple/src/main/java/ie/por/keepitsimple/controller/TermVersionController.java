package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.requestbody.termversion.AddTermVersionReqBody;
import ie.por.keepitsimple.service.TermVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/term/{id}")
public class TermVersionController {

    @Autowired
    private TermVersionService termVersionService;

    @PostMapping(value="/termversions")
    public void addTermVersion(@RequestBody AddTermVersionReqBody requestBody, @PathVariable Long id) {
        termVersionService.add(requestBody, id);
    }


}
