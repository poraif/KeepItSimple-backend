package ie.por.keepitsimple.controller;

import ie.por.keepitsimple.dto.requestbody.account.LoginReqBody;
import ie.por.keepitsimple.dto.responsebody.UnapprovedVersionInfo;
import ie.por.keepitsimple.service.AccountService;
import ie.por.keepitsimple.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<UnapprovedVersionInfo> getUnapprovedVersions() throws Exception {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String userRole = authorities.get(0).getAuthority();
        return adminService.getUnapprovedVersions(userRole);
    }

    @PostMapping(value="/login")
    public String login(@RequestBody LoginReqBody body) {
        return accountService.login(body);
    }
}