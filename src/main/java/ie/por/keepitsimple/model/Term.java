package ie.por.keepitsimple.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "term_id_seq")
    @SequenceGenerator(name = "term_id_seq", sequenceName = "term_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dateUpdated = LocalDateTime.now();

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TermVersion> termVersions;

    @ManyToOne
    @JoinColumn(name = "current_version_id", referencedColumnName = "id", nullable = true)
    private TermVersion currentVersion;

    @ManyToMany(mappedBy = "terms")
    private Set<TermCollection> collections;

    public Term() {}

    public Term(String name, String category, LocalDateTime dateUpdated) {
        this.name = name;
        this.category = category;
        this.dateUpdated = dateUpdated;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<TermVersion> getTermVersions() {
        return termVersions;
    }

    public void setTermVersions(List<TermVersion> termVersions) {
        this.termVersions = termVersions;
    }

    public TermVersion getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(TermVersion currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Set<TermCollection> getCollections() {
        return collections;
    }

    public void setCollections(Set<TermCollection> collections) {
        this.collections = collections;
    }
}
