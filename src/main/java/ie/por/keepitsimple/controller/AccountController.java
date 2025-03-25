package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.dto.requestbody.account.LoginReqBody;
import ie.por.keepitsimple.dto.requestbody.account.SignupReqBody;
import ie.por.keepitsimple.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value="/signup")
    public String signup(@RequestBody SignupReqBody body) {
        return accountService.signup(body);
    }

    @PostMapping(value="/login")
    public String login(@RequestBody LoginReqBody body) {
        return accountService.login(body);
    }
}


