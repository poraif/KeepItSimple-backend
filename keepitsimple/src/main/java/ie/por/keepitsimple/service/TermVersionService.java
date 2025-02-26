package ie.por.keepitsimple.service;

import ie.por.keepitsimple.model.Term;
import ie.por.keepitsimple.model.TermVersion;
import ie.por.keepitsimple.repository.TermVersionRepository;
import ie.por.keepitsimple.requestbody.termversion.AddTermVersionReqBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TermVersionService {

    @Autowired
    private TermVersionRepository termVersionRepository;

    @Autowired
    private TermService termService;

    public void add(AddTermVersionReqBody requestBody, Long termId) {
        TermVersion termVersion = new TermVersion();
        termVersion.setShortDef(requestBody.getShortDef());
        termVersion.setLongDef(requestBody.getLongDef());
        termVersion.setCodeSnippet(requestBody.getCodeSnippet());
        termVersion.setExampleUsage(requestBody.getExampleUsage());
        termVersion.setTerm(termService.findTermById(termId));
        termVersionRepository.save(termVersion);
    }
}
