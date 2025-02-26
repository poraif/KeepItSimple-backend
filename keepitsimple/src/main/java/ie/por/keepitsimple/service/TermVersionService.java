package ie.por.keepitsimple.service;

import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.repository.TermVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermVersionService {

    @Autowired
    private TermVersionRepository termVersionRepository;

    public void add(TermVersion termVersion) {
        termVersionRepository.save(termVersion);
    }
}
