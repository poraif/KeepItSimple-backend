package ie.por.keepitsimple.service;

import ie.por.keepitsimple.dto.responsebody.term.TermAndCurrentVersion;
import ie.por.keepitsimple.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private TermRepository termRepository;

    public List<TermAndCurrentVersion> getUnapprovedVersions() {
        return termRepository.getUnapprovedVersions();
    }
}
