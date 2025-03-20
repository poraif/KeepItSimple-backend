package ie.por.keepitsimple.config;

import ie.por.keepitsimple.model.Account;
import ie.por.keepitsimple.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("user " + username + " not found");
        }
        List<GrantedAuthority> authority = List.of(new SimpleGrantedAuthority(account.getRole()));
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                authority
        );
    }
}
