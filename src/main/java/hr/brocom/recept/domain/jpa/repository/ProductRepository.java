package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.domain.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {}
