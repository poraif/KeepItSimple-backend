package ie.por.keepitsimple.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'editor'")
    private String role;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDateTime dateAdded;

    @Column
    @Temporal(TemporalType.DATE)  //for the db storage
    @DateTimeFormat(pattern = "dd-MM-yyyy") //for the serialisation output on frotend
    private LocalDateTime dateUpdated;

//    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, orphanRemoval = true)
//    private List<TermVersion> termVersions;
//
//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Collection> collections;

    @PreUpdate
    public void setLastUpdate() {
        this.dateUpdated = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
