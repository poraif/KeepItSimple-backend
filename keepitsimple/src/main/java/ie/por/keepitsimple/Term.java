package ie.por.keepitsimple;

public class Term {

    private int id;
    private String name;
    private String description;
    private String codeSnippet;
    private String topic;

    public Term() {}

    public Term(int id, String name, String description, String codeSnippet, String topic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.codeSnippet = codeSnippet;
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
