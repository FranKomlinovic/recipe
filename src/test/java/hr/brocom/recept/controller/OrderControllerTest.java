package hr.brocom.recept.controller;

import hr.brocom.recept.domain.jpa.DelivererJpaImpl;
import hr.brocom.recept.domain.jpa.UserJpaImpl;
import hr.brocom.recept.domain.jpa.entity.OrdersEntity;
import hr.brocom.recept.domain.jpa.repository.DelivererRepository;
import hr.brocom.recept.domain.jpa.repository.OrdersRepository;
import hr.brocom.recept.model.OrderDto;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.model.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    private static final LocalDateTime CREATED_TIME = LocalDateTime.now();
    private static final LocalDateTime DELIVERY_DATE_TIME = LocalDateTime.of(2019, 12, 22, 16, 30);
    private static final String ADDRESS = "Nova Ulica 2";
    private static final String CODE = "ORD1";
    private static final BigDecimal PRICE = new BigDecimal("150.00");
    private static final Boolean CASH_ON_DELIVERY = false;
    private static final Boolean DELIVERED = false;
    private static final String ADDITIONAL_INFO = "No info";
    private static final String USER_MAIL = "user@mail.com";
    private static final String USER_USERNAME = "User";
    private static final String DELIVERER_CODE = "DEL0";

    // Import.sql order
    private static final LocalDateTime INIT_DELIVERY_DATE_TIME = LocalDateTime.of(2019, 7, 10, 13, 50, 45);
    private static final LocalDateTime INIT_CREATED_TIME = LocalDateTime.of(2019, 7, 10, 13, 50, 45);
    private static final String INIT_ADDRESS = "Testna Ulica 1";
    private static final String INIT_CODE = "ORD0";
    private static final BigDecimal INIT_PRICE = new BigDecimal("200.00");
    private static final Boolean INIT_CASH_ON_DELIVERY = true;
    private static final Boolean INIT_DELIVERED = true;
    private static final String INIT_ADDITIONAL_INFO = "nema nekih info";

    @Autowired
    UserJpaImpl userJpa;

    @Autowired
    DelivererJpaImpl delivererJpa;

    @Autowired
    DelivererRepository delivererRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderController orderController;

    @Test
    @Transactional
    public void getAllActiveOrders() {
        RestDto<List<OrderDto>> response = orderController.getAllActiveOrders();

        assertNotNull(response.getData());
        assertTrue(response.isSuccess());
        Optional<OrderDto> orderOptional = response.getData()
                .stream()
                .filter(orderDto -> INIT_CODE.equals(orderDto.getCode()))
                .findFirst();

        assertTrue(orderOptional.isPresent());
        OrderDto orderDto = orderOptional.get();
        assertNotNull(orderDto.getDeliverer());
        assertNotNull(orderDto.getUser());
        assertEquals(INIT_CODE, orderDto.getCode());
    }

    @Test
    @Transactional
    public void addOrder() {
        orderController.addOrder(createInitOrder());
        OrdersEntity orderFromDatabase = findByCode(CODE);

        assertEquals(CODE, orderFromDatabase.getCode());
        assertEquals(ADDITIONAL_INFO, orderFromDatabase.getAdditionalInfo());
        assertEquals(ADDRESS, orderFromDatabase.getAddress());
        assertEquals(CREATED_TIME, orderFromDatabase.getCreatedTime());
        assertEquals(DELIVERY_DATE_TIME, orderFromDatabase.getDeliveryDateTime());
        assertEquals(DELIVERED, orderFromDatabase.getDelivered());
        assertEquals(PRICE, orderFromDatabase.getPrice());
        assertEquals(CASH_ON_DELIVERY, orderFromDatabase.getCashOnDelivery());

        // User koji se nalazi u orderu
        assertEquals(USER_MAIL,
                orderFromDatabase.getUser()
                        .getMail());
        // Deliverer koji se nalazi u orderu
        assertEquals(DELIVERER_CODE,
                orderFromDatabase.getDeliverer()
                        .getCode());
    }


    @Test
    @Transactional
    public void updateOrder() {
        // Init data
        OrderDto initOrder = createInitOrder();

        // Check if not exists yet
        assertFalse(ordersRepository.findByCode(CODE)
                .isPresent());

        // Check if values from init are present
        Optional<OrdersEntity> entityFromInit = ordersRepository.findByCode(INIT_CODE);
        assertTrue(entityFromInit.isPresent());
        OrdersEntity ordersEntity = entityFromInit.get();

        // Assert if everything is as in import script
        assertEquals(true, ordersEntity.getActive());
        assertEquals(INIT_CASH_ON_DELIVERY, ordersEntity.getCashOnDelivery());
        assertEquals(INIT_DELIVERED, ordersEntity.getDelivered());
        assertEquals(INIT_ADDITIONAL_INFO, ordersEntity.getAdditionalInfo());
        assertEquals(INIT_ADDRESS, ordersEntity.getAddress());
        assertEquals(INIT_DELIVERY_DATE_TIME, ordersEntity.getDeliveryDateTime());
        assertEquals(INIT_CREATED_TIME, ordersEntity.getCreatedTime());
        assertEquals(INIT_PRICE, ordersEntity.getPrice());

        // Change information from import script to information from initOrder method by changing code
        initOrder.setCode(INIT_CODE);

        // Execute update
        orderController.updateOrder(initOrder);

        OrdersEntity updatedOrder = findByCode(INIT_CODE);

        assertEquals(true, updatedOrder.getActive());
        assertEquals(CASH_ON_DELIVERY, updatedOrder.getCashOnDelivery());
        assertEquals(DELIVERED, updatedOrder.getDelivered());
        assertEquals(ADDITIONAL_INFO, updatedOrder.getAdditionalInfo());
        assertEquals(ADDRESS, updatedOrder.getAddress());
        assertEquals(DELIVERY_DATE_TIME, updatedOrder.getDeliveryDateTime());
        assertEquals(CREATED_TIME, updatedOrder.getCreatedTime());
        assertEquals(PRICE, updatedOrder.getPrice());
    }

    @Test
    @Transactional
    public void deactivateOrder() {
        OrdersEntity orderByCode = findByCode(INIT_CODE);

        assertTrue(orderByCode.getActive());

        orderController.deactivateOrder(INIT_CODE);

        orderByCode = findByCode(INIT_CODE);

        assertFalse(orderByCode.getActive());
    }

    private OrderDto createInitOrder() {
        OrderDto orderDto = new OrderDto();
        orderDto.setCode(CODE);
        orderDto.setDeliverer(delivererJpa.findDelivererByCode(DELIVERER_CODE));

        orderDto.setUser(getInitUser());
        orderDto.setCreatedTime(CREATED_TIME);
        orderDto.setDeliveryDateTime(DELIVERY_DATE_TIME);
        orderDto.setAddress(ADDRESS);
        orderDto.setPrice(PRICE);
        orderDto.setCashOnDelivery(CASH_ON_DELIVERY);
        orderDto.setDelivered(DELIVERED);
        orderDto.setAdditionalInfo(ADDITIONAL_INFO);

        return orderDto;
    }

    private OrdersEntity findByCode(String code) {
        return ordersRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }

    private UserDto getInitUser() {
        return userJpa.getAllUsers()
                .stream()
                .filter(userDto -> USER_USERNAME.equals(userDto.getUsername()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Nije pronaden entity"));
    }

}