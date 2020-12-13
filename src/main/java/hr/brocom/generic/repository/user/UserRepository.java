package hr.brocom.generic.repository.user;

import hr.brocom.generic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
