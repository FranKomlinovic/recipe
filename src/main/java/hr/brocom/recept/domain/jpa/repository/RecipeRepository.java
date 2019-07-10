package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.domain.jpa.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {}
