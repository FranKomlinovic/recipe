package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.domain.jpa.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    Optional<RecipeEntity> findByCode(String code);
}
