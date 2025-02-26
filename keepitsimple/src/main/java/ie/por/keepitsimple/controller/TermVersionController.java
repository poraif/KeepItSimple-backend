package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.service.TermVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/term/{id}")
public class TermVersionController {

    @Autowired
    private TermVersionService termVersionService;

    @PostMapping(value="/termversions", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addTermVersion(@RequestBody TermVersion termVersion) {
        termVersionService.add(termVersion);
    }


}
