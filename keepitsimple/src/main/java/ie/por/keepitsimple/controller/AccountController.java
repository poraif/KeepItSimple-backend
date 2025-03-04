package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.config.JwtUtil;
import ie.por.keepitsimple.model.Account;
import ie.por.keepitsimple.repository.AccountRepository;
import ie.por.keepitsimple.requestbody.account.LoginReqBody;
import ie.por.keepitsimple.requestbody.account.SignupReqBody;
import ie.por.keepitsimple.requestbody.term.AddTermReqBody;
import ie.por.keepitsimple.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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


