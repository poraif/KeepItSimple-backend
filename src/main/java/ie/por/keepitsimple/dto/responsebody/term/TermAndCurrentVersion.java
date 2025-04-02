package ie.por.keepitsimple.dto.responsebody.term;

public interface TermAndCurrentVersion {
    String getName();
    Long getId();
    String getUsername();
    String getCategory();
    String getShortDef();
    String getLongDef();
    String getCodeSnippet();
    String getExampleUsage();
}