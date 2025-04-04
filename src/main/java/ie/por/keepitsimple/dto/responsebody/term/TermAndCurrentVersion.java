package ie.por.keepitsimple.dto.responsebody.term;

public interface TermAndCurrentVersion {
    String getName();
    Long getId();
    String getUsername();
    Integer getLoggedInUserVote();
    String getCategory();
    String getShortDef();
    String getLongDef();
    String getCodeSnippet();
    String getExampleUsage();
}