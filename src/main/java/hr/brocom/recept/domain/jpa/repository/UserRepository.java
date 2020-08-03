package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.domain.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
