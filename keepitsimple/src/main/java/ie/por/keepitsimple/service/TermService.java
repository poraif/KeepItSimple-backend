package ie.por.keepitsimple.service;

import ie.por.keepitsimple.model.Term;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TermService {

    private int id;
    private String name;
    private String description;
    private String codeSnippet;
    private String topic;

    private List<Term> terms =Arrays.asList(
            new Term(1234, "test", "test", "test", "test")
            );

    public List<Term> getTerms() {
        return terms;
    }

}
