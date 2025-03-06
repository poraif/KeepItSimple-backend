package ie.por.keepitsimple.ai;

import ie.por.keepitsimple.requestbody.termversion.AddTermVersionReqBody;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AiService {

    public AddTermVersionReqBody generateTerm(String termName) {
        AddTermVersionReqBody reqBody = new AddTermVersionReqBody();
        return reqBody;
    }

    public boolean checkTerm(String termName) {
        return true;
    }
}
