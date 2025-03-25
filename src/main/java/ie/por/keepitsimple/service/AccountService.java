package ie.por.keepitsimple.service;

import ie.por.keepitsimple.config.JwtUtil;
import ie.por.keepitsimple.model.Account;
import ie.por.keepitsimple.repository.AccountRepository;
import ie.por.keepitsimple.dto.requestbody.account.LoginReqBody;
import ie.por.keepitsimple.dto.requestbody.account.SignupReqBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String signup(SignupReqBody body) {
        if (accountRepository.existsByUsername(body.getUsername())) {
            return "Username is already in use";
        }
        Account account = new Account(
                body.getUsername(),
                body.getEmail(),
                passwordEncoder.encode(body.getPassword()),
                "editor"
        );
        accountRepository.save(account);
        return "Account created";
    }

    public String login(LoginReqBody body) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        body.getUsername(),
                        body.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername());
        log.info(token);
        return token;
    }

    public Account findAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

}
