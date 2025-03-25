package ie.por.keepitsimple.repository;

import ie.por.keepitsimple.model.Vote;
import ie.por.keepitsimple.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, VoteId> {
}
