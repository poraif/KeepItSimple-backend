package ie.por.keepitsimple.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TermVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String shortDef;

    @Column(nullable = false)
    private String longDef;

    @Column
    private String codeSnippet;

    @Column(nullable = false)
    private String exampleUsage;

    @Column(nullable = false)
    private Boolean approved = false;

    @ManyToOne
    @JoinColumn(name = "approved_by", referencedColumnName = "id", nullable = true)
    private Account approvedBy;

    @ManyToOne
    @JoinColumn(name = "term_id", referencedColumnName = "id", nullable = true)
    private Term term;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private Account account;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime dateAdded = LocalDateTime.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Account getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Account approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }
}
