package ie.por.keepitsimple.repository;

import ie.por.keepitsimple.model.TermVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermVersionRepository extends JpaRepository<TermVersion, Long> {


}
