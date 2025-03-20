package ie.por.keepitsimple.dto.requestbody.term;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddTermAndVersionReqBody {

    @NotBlank(message = "Add a name for the term")
    public String name;

    @NotBlank(message = "choose a category that best fits this term")
    public String category;

    @NotBlank(message = "add an ELI5 definition - 200 characters or less")
    @Size(max = 200, message = "too long! Make it simpler")
    private String shortDef;

    @NotBlank(message = "Expand on your short definition here")
    private String longDef;

    private String codeSnippet;

    @NotBlank(message = "Suggest a real-world use case for using this")
    private String exampleUsage;

    public AddTermAndVersionReqBody(String name, String category, String shortDef, String longDef, String codeSnippet, String exampleUsage) {
        this.name = name;
        this.category = category;
        this.shortDef = shortDef;
        this.longDef = longDef;
        this.codeSnippet = codeSnippet;
        this.exampleUsage = exampleUsage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public AddTermAndVersionReqBody() {
    }


}
