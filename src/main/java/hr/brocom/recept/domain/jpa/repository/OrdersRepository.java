package hr.brocom.recept.domain.jpa.repository;

import hr.brocom.recept.domain.jpa.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {

    OrdersEntity findByAddress(String address);

    Optional<OrdersEntity> findByCode(String code);

    List<OrdersEntity> findAllByActiveTrue();
}
