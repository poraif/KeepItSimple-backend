package ie.por.keepitsimple.service;

import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.repository.TermRepository;
import ie.por.keepitsimple.requestbody.term.AddTermReqBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;


    public void add(AddTermReqBody requestBody) {
        Term term = termRepository.findTermByName(requestBody.getName());
        if (term == null) {
            term = new Term();
            term.setName(requestBody.getName());
            term.setCategory(requestBody.getCategory());
            termRepository.save(term);
        }
        else {
            Exception e = new Exception();
            log.error("term exists in db", e);
        }




    }
}
