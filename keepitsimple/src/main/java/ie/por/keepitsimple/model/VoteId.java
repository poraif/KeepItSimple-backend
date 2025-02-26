package ie.por.keepitsimple.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoteId implements Serializable {

    private Long userId;

    private Long versionId;

    public VoteId() {}

    public VoteId(Long userId, Long versionId) {
        this.userId = userId;
        this.versionId = versionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteId voteId = (VoteId) o;
        return Objects.equals(userId, voteId.userId) &&
                Objects.equals(versionId, voteId.versionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, versionId);
    }
}