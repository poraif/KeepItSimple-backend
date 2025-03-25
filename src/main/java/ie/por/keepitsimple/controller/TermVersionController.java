package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.dto.requestbody.termversion.AddTermVersionReqBody;
import ie.por.keepitsimple.service.TermVersionService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
