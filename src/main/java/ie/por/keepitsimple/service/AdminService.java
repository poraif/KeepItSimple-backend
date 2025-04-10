package ie.por.keepitsimple.service;

import ie.por.keepitsimple.dto.responsebody.UnapprovedVersionInfo;
import ie.por.keepitsimple.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private TermRepository termRepository;

    public List<UnapprovedVersionInfo> getUnapprovedVersions(String userRole) throws Exception {
        if (userRole.equals("ROLE_ADMIN")) {
            List<UnapprovedVersionInfo> unapproved = termRepository.getUnapprovedVersions();
            System.out.println(unapproved);
            return unapproved;
        }
        else {
            throw new Exception("User not admin");
        }
    };
}
