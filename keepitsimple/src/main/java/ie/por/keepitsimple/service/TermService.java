package ie.por.keepitsimple.service;

import ie.por.keepitsimple.ai.AiService;
import ie.por.keepitsimple.dto.requestbody.term.AddTermAndVersionReqBody;
import ie.por.keepitsimple.dto.responsebody.term.TermAndCurrentVersion;
import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.repository.TermRepository;
import ie.por.keepitsimple.dto.requestbody.term.AddTermReqBody;
import ie.por.keepitsimple.repository.TermVersionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private AiService aiService;

    @Autowired
    private TermVersionRepository termVersionRepository;



    public void add(AddTermReqBody requestBody) {
        Term term = termRepository.findTermByName(requestBody.getName());
        if (term == null) {
            term = new Term();
            term.setName(requestBody.getName());
            term.setCategory(requestBody.getCategory());
            termRepository.save(term);
        } else {
            Exception e = new Exception();
            log.error("term exists in db", e);
        }
    }

    public Term findTermById(Long id) {
        return termRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Term not found: " + id));
    }

    public Term findTermByName(String name) {
        return termRepository.findTermByName(name);
    }

    public TermAndCurrentVersion findTermAndCurrentVersionByName(String term) {
        return termRepository.getTermAndCurrentVersionByName(term);
    }

    public Term findTermByName(String name) {
        return termRepository.findTermByName(name);
    }

    public Optional<TermAndCurrentVersion> searchTerm(String term) {
        Term foundTerm = termRepository.findTermByName(term);
        if (foundTerm != null) {
            return Optional.of(termRepository.getTermAndCurrentVersionByName(term));
        }
            boolean isValidTerm = aiService.checkTerm(term);
            if (!isValidTerm) {
                log.error("Looks like not a valid term");
                return Optional.empty();
            }
                Term newTerm = new Term();
                newTerm.setName(term);
                newTerm.setCategory(aiService.generateTermCategory(term));
                termRepository.save(newTerm);
                System.out.println("New term: " + newTerm);

                TermVersion newTermVersion = aiService.generateTermVersion(term);
                newTermVersion.setApproved(true);
                newTermVersion.setTerm(newTerm);
                termVersionRepository.save(newTermVersion);
                System.out.println("New term version: " + newTermVersion);

                newTerm.setCurrentVersion(newTermVersion);
                termRepository.save(newTerm);

                return Optional.of(termRepository.getTermAndCurrentVersionByName(term));
            }

    public List<String> getAllTermNames() {
        List<String> termNames = termRepository.findAllTermNames();
        if (termNames.isEmpty()) {
            log.info("Term list returned empty");
            return null;
        }
        return termNames;
    }

    public void addTermAndVersion(AddTermAndVersionReqBody requestBody) {
        Term foundTerm = termRepository.findTermByName(requestBody.getName());
        if (foundTerm == null) {
            foundTerm = new Term();
            foundTerm.setName(requestBody.getName());
            foundTerm.setCategory(requestBody.getCategory());
            termRepository.save(foundTerm);
            log.info("Term added: {}", foundTerm);
        }

    }
}
