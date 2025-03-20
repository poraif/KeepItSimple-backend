package ie.por.keepitsimple.service;

import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.repository.TermVersionRepository;
import ie.por.keepitsimple.dto.requestbody.termversion.AddTermVersionReqBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermVersionService {

    @Autowired
    private TermVersionRepository termVersionRepository;

    @Autowired
    private TermService termService;

    public void add(AddTermVersionReqBody requestBody, String name) {
        TermVersion termVersion = new TermVersion();
        Term term = termService.findTermByName(name);
        termVersion.setShortDef(requestBody.getShortDef());
        termVersion.setLongDef(requestBody.getLongDef());
        termVersion.setCodeSnippet(requestBody.getCodeSnippet());
        termVersion.setExampleUsage(requestBody.getExampleUsage());
        termVersion.setTerm(term);

        termVersionRepository.save(termVersion);
    }
}
