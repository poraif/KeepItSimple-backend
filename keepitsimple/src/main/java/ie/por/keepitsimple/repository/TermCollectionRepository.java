package ie.por.keepitsimple.repository;

import ie.por.keepitsimple.model.TermCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermCollectionRepository extends JpaRepository<TermCollection, Long> {
}
