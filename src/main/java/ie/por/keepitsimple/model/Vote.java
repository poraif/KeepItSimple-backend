package ie.por.keepitsimple.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {

    @EmbeddedId
    private VoteId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private Account account;

    @ManyToOne
    @MapsId("versionId")
    @JoinColumn(name = "version_id", nullable = false)
    private TermVersion termVersion;

    @Column(nullable = false)
    private int voteValue;

    public Vote() {}

    public Vote(Account account, TermVersion termVersion, int voteValue) {
        this.id = new VoteId(account.getId(), termVersion.getId());
        this.account = account;
        this.termVersion = termVersion;
        this.voteValue = voteValue;
    }

    public VoteId getId() {
        return id;
    }

    public void setId(VoteId id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TermVersion getTermVersion() {
        return termVersion;
    }

    public void setTermVersion(TermVersion termVersion) {
        this.termVersion = termVersion;
    }

    public int getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(int voteValue) {
        this.voteValue = voteValue;
    }
}