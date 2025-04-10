package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.dto.requestbody.account.LoginReqBody;
import ie.por.keepitsimple.dto.requestbody.account.SignupReqBody;
import ie.por.keepitsimple.dto.responsebody.term.TermAndCurrentVersion;
import ie.por.keepitsimple.service.AccountService;
import ie.por.keepitsimple.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AdminService adminService;

    @GetMapping(value="/unapprovedversions")
    public List<TermAndCurrentVersion> getUnapprovedVersions() {
        return adminService.getUnapprovedVersions();
    }

    @PostMapping(value="/login")
    public String login(@RequestBody LoginReqBody body) {
        return accountService.login(body);
    }
}