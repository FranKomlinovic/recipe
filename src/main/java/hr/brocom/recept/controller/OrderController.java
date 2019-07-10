package hr.brocom.recept.controller;

import hr.brocom.recept.model.OrderDto;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderService orderService;

    @GetMapping("/list")
    public RestDto<List<OrderDto>> getAllActiveOrders() {
        log.trace("Get all active orders");
        long time = System.currentTimeMillis();
        List<OrderDto> orders = orderService.getAllActiveOrders();
        log.trace("getAllActiveorders finished in {} ms", System.currentTimeMillis() - time);
        log.trace("getAllActiveorders returned {} results", orders.size());
        return RestDto.success(orders);
    }

    @PostMapping("")
    public RestDto<Void> addOrder(@RequestBody @Valid OrderDto orderDto) {
        log.info("Adding order: {}...", orderDto.getCode());
        log.trace("Order: {}", orderDto);
        long time = System.currentTimeMillis();
        orderService.addOrder(orderDto);
        log.trace("addOrder finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Order uspješno dodan!");
    }

    @PutMapping("")
    public RestDto<Void> updateOrder(@RequestBody OrderDto orderDto) {
        log.info("Update order: {}...", orderDto.getCode());
        log.trace("Order: " + orderDto);
        long time = System.currentTimeMillis();
        orderService.updateOrder(orderDto);
        log.trace("updateOrder finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Order uspješno ažuriran");
    }

    @DeleteMapping("/{code}")
    public RestDto<Void> deactivateOrder(@PathVariable String code) {
        log.info("Deleting order: {}...", code);
        long time = System.currentTimeMillis();
        orderService.deactivateOrder(code);
        log.trace("deleteOrder finished in {} ms", System.currentTimeMillis() - time);
        return RestDto.success("Order uspješno obrisan");
    }
}

