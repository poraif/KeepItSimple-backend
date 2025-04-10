package ie.por.keepitsimple.repository;

import ie.por.keepitsimple.model.Account;
import ie.por.keepitsimple.model.TermCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TermCollectionRepository extends JpaRepository<TermCollection, Long> {
    Optional<TermCollection> findByName(String name);

    TermCollection existsTermCollectionByNameAndAccount(String name, Account account);

    
}
