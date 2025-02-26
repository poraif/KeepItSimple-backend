package ie.por.keepitsimple.repository;

import ie.por.keepitsimple.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
