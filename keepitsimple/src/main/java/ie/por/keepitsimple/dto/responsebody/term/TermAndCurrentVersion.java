package ie.por.keepitsimple.dto.responsebody.term;

import jakarta.validation.constraints.NotBlank;

public class TermAndCurrentVersion {

    public String name;

    public String category;

    private String shortDef;

    private String longDef;

    private String codeSnippet;

    private String exampleUsage;

    public TermAndCurrentVersion() {
    }

    public TermAndCurrentVersion(String name, String category, String shortDef, String longDef, String exampleUsage) {
        this.name = name;
        this.category = category;
        this.shortDef = shortDef;
        this.longDef = longDef;
        this.exampleUsage = exampleUsage;
    }

    public TermAndCurrentVersion(String name, String category, String shortDef, String longDef, String codeSnippet, String exampleUsage) {
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
}