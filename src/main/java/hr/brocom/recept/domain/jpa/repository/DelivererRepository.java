package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.domain.jpa.entity.DelivererEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DelivererRepository extends JpaRepository<DelivererEntity, Long> {

    Optional<DelivererEntity> findByCode(String code);
}
