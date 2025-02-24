package ie.por.keepitsimple.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
