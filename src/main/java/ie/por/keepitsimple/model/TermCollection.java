package ie.por.keepitsimple.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TermCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "term_collection_id_seq")
    @SequenceGenerator(name = "term_collection_id_seq", sequenceName = "term_collection_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private LocalDateTime dateAdded = LocalDateTime.now();

    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dateUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @ManyToMany
    @JoinTable(name = "contains", joinColumns = @JoinColumn(name = "term_collection_id"), inverseJoinColumns = @JoinColumn(name = "term_id"))
    private Set<Term> terms = new HashSet<>();

    public TermCollection() {}

    public TermCollection(String name, String description, LocalDateTime dateAdded, LocalDateTime dateUpdated, Account account) {
        this.name = name;
        this.description = description;
        this.dateAdded = dateAdded;
        this.dateUpdated = dateUpdated;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    @PreUpdate
    public void setDateUpdated() {
        this.dateUpdated = LocalDateTime.now();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Term> getTerms() {
        return terms;
    }

    public void setTerms(Set<Term> terms) {
        this.terms = terms;
    }
}
