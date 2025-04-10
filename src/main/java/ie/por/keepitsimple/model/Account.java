package ie.por.keepitsimple.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_seq")
    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Column(nullable = false)
    private String role;

    @Column
    private LocalDateTime dateAdded = LocalDateTime.now();

    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy") //for frontend
    private LocalDateTime dateUpdated;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<TermVersion> termVersions;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TermCollection> termCollections;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vote> votes;

    public Account() {
    }

    public Account(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role != null ? role : "editor";
    }

    public Account(String username, String email, String password, String role, LocalDateTime dateAdded, LocalDateTime dateUpdated) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role != null ? role : "editor";
        this.dateAdded = dateAdded;
        this.dateUpdated = dateUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    @PreUpdate
    public void updateTimestamp() {
        this.dateUpdated = LocalDateTime.now();
    }

    public List<TermVersion> getTermVersions() {
        return termVersions;
    }

    public void setTermVersions(List<TermVersion> termVersions) {
        this.termVersions = termVersions;
    }

    public List<TermCollection> getTermCollections() {
        return termCollections;
    }

    public void setTermCollections(List<TermCollection> termCollections) {
        this.termCollections = termCollections;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
