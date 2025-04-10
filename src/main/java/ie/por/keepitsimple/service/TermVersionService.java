package ie.por.keepitsimple.service;

import ie.por.keepitsimple.model.Account;
import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.repository.TermRepository;
import ie.por.keepitsimple.repository.TermVersionRepository;
import ie.por.keepitsimple.dto.requestbody.termversion.AddTermVersionReqBody;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TermVersionService {

    @Autowired
    private TermVersionRepository termVersionRepository;

    @Autowired
    private TermService termService;
    @Autowired
    private TermRepository termRepository;

    @Autowired
    private AccountService accountService;

    public void add(AddTermVersionReqBody requestBody, String name, String username) {
        Account account = accountService.findAccountByUsername(username);
        TermVersion termVersion = new TermVersion();
        Term term = termService.findTermByName(name);
        termVersion.setShortDef(requestBody.getShortDef());
        termVersion.setLongDef(requestBody.getLongDef());
        termVersion.setCodeSnippet(requestBody.getCodeSnippet());
        termVersion.setExampleUsage(requestBody.getExampleUsage());
        termVersion.setAccount(account);
        termVersion.setTerm(term);

        termVersionRepository.save(termVersion);
    }

    public void updateTermVersion(AddTermVersionReqBody requestBody, Long id, String name, String username) throws Exception {
        TermVersion termVersion = termVersionRepository.getTermVersionById(id);
        System.out.println(termVersion);
        if (termVersion == null) {
            throw new Exception("no term version returned from id");
        }

        if (!termVersion.getAccount().getUsername().equals(username)) {
            throw new Exception("wrong user");
        }

        if (!(termVersion.getTerm().getName().equals(name))) {
            throw new Exception("The term version doesnt match the term");
        }

        termVersion.setShortDef(requestBody.getShortDef());
        termVersion.setLongDef(requestBody.getLongDef());
        termVersion.setCodeSnippet(requestBody.getCodeSnippet());
        termVersion.setExampleUsage(requestBody.getExampleUsage());
        termVersionRepository.save(termVersion);
    }

    @Transactional
    public void deleteTermVersion(Long id, String name, String username) throws Exception {
        TermVersion termVersion = termVersionRepository.getTermVersionById(id);
        Term term = termVersion.getTerm();

        if (termVersion == null) {
            throw new Exception("No term version returned from id");
        }

        if (!term.getName().equals(name)) {
            throw new Exception("The term version doesn't match the term");
        }

        if (!termVersion.getAccount().getUsername().equals(username)) {
            throw new Exception("invalid user");
        }

        if (term.getCurrentVersion().equals(termVersion)) {
            term.setCurrentVersion(null);
            termRepository.save(term);
        }

        termVersionRepository.delete(termVersion);
    }

    TermVersion findTermVersionById(Long id) {
        Optional<TermVersion> termVersion = termVersionRepository.findById(id);
        if (termVersion.isPresent()) {
            return termVersion.get();
        }
        return null;
    }
}
