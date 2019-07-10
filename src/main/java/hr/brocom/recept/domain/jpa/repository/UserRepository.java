package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.domain.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByMail(String mail);

    Optional<UserEntity> findByNickname(String username);
}
