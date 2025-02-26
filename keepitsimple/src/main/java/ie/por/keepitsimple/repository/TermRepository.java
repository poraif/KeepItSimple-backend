package ie.por.keepitsimple.repository;

import ie.por.keepitsimple.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {

    public Term findTermByName(String name);

}
