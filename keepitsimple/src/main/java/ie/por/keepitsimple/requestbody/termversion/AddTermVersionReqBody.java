package ie.por.keepitsimple.requestbody.termversion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddTermVersionReqBody {

    @NotBlank(message = "add an ELI5 definition - 200 characters or less")
    @Size(max = 200, message = "too long! Make it simpler")
    private String shortDef;

    @NotBlank(message = "Expand on your short definition here")
    private String longDef;

    private String codeSnippet;

    @NotBlank(message = "Suggest a real-world use case for using this")
    private String exampleUsage;

    public AddTermVersionReqBody() {
    }

    public AddTermVersionReqBody(String shortDef, String longDef, String codeSnippet, String exampleUsage) {
        this.shortDef = shortDef;
        this.longDef = longDef;
        this.codeSnippet = codeSnippet;
        this.exampleUsage = exampleUsage;
    }

    public String getShortDef() {
        return shortDef;
    }

    public void setShortDef(String shortDef) {
        this.shortDef = shortDef;
    }

    public String getLongDef() {
        return longDef;
    }

    public void setLongDef(String longDef) {
        this.longDef = longDef;
    }

    public String getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }

    public String getExampleUsage() {
        return exampleUsage;
    }

    public void setExampleUsage(String exampleUsage) {
        this.exampleUsage = exampleUsage;
    }



}
