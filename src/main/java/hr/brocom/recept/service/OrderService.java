package hr.brocom.recept.service;

import hr.brocom.recept.domain.jpa.OrderJpaImpl;
import hr.brocom.recept.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderJpaImpl orderJpa;

    public List<OrderDto> getAllActiveOrders() {
        return orderJpa.getAllActiveOrders();
    }

    public void addOrder(OrderDto orderDto) {
        orderJpa.addOrder(orderDto);
    }

    public void updateOrder(OrderDto orderDto) {
        orderJpa.updateOrder(orderDto);
    }

    public void deactivateOrder(String code) {
        orderJpa.deactivateOrder(code);
    }
}
