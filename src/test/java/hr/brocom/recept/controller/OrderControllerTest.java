package hr.brocom.recept.controller;

import hr.brocom.recept.domain.jpa.DelivererJpaImpl;
import hr.brocom.recept.domain.jpa.UserJpaImpl;
import hr.brocom.recept.domain.jpa.entity.OrdersEntity;
import hr.brocom.recept.domain.jpa.repository.DelivererRepository;
import hr.brocom.recept.domain.jpa.repository.OrdersRepository;
import hr.brocom.recept.model.OrderDto;
import hr.brocom.recept.model.RestDto;
import hr.brocom.recept.model.UserDto;
import org.junit.Assert;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    private static final LocalDateTime CREATED_TIME       = LocalDateTime.now();
    private static final LocalDateTime DELIVERY_DATE_TIME = LocalDateTime.of(2019, 12, 22, 16, 30);
    private static final String        ADDRESS            = "Nova Ulica 2";
    private static final String        CODE               = "ORD1";
    private static final BigDecimal    PRICE              = new BigDecimal("150.00");
    private static final Boolean       CASH_ON_DELIVERY   = false;
    private static final Boolean       DELIVERED          = false;
    private static final String        ADDITIONAL_INFO    = "No info";
    private static final String        USER_MAIL          = "user@mail.com";
    private static final String        DELIVERER_CODE     = "DEL0";

    // Import.sql order
    private static final LocalDateTime INIT_DELIVERY_DATE_TIME = LocalDateTime.of(2019, 7, 10, 13, 50, 45);
    private static final LocalDateTime INIT_CREATED_TIME       = LocalDateTime.of(2019, 7, 10, 13, 50, 45);
    private static final String        INIT_ADDRESS            = "Testna Ulica 1";
    private static final String        INIT_CODE               = "ORD0";
    private static final BigDecimal    INIT_PRICE              = new BigDecimal("200.00");
    private static final Boolean       INIT_CASH_ON_DELIVERY   = true;
    private static final Boolean       INIT_DELIVERED          = true;
    private static final String        INIT_ADDITIONAL_INFO    = "nema nekih info";

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

        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.isSuccess());
        Optional<OrderDto> orderOptional = response.getData()
                                                   .stream()
                                                   .filter(orderDto -> INIT_CODE.equals(orderDto.getCode()))
                                                   .findFirst();

        Assert.assertTrue(orderOptional.isPresent());
        OrderDto orderDto = orderOptional.get();
        Assert.assertNotNull(orderDto.getDeliverer());
        Assert.assertNotNull(orderDto.getUser());
        Assert.assertEquals(INIT_CODE, orderDto.getCode());
    }

    @Test
    @Transactional
    public void addOrder() {
        orderController.addOrder(createInitOrder());
        OrdersEntity orderFromDatabase = findByCode(CODE);

        Assert.assertEquals(CODE, orderFromDatabase.getCode());
        Assert.assertEquals(ADDITIONAL_INFO, orderFromDatabase.getAdditionalInfo());
        Assert.assertEquals(ADDRESS, orderFromDatabase.getAddress());
        Assert.assertEquals(CREATED_TIME, orderFromDatabase.getCreatedTime());
        Assert.assertEquals(DELIVERY_DATE_TIME, orderFromDatabase.getDeliveryDateTime());
        Assert.assertEquals(DELIVERED, orderFromDatabase.getDelivered());
        Assert.assertEquals(PRICE, orderFromDatabase.getPrice());
        Assert.assertEquals(CASH_ON_DELIVERY, orderFromDatabase.getCashOnDelivery());

        // User koji se nalazi u orderu
        Assert.assertEquals(USER_MAIL,
                            orderFromDatabase.getUser()
                                             .getMail());
        // Deliverer koji se nalazi u orderu
        Assert.assertEquals(DELIVERER_CODE,
                            orderFromDatabase.getDeliverer()
                                             .getCode());
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
                      .filter(userDto -> USER_MAIL.equals(userDto.getMail()))
                      .findFirst()
                      .orElseThrow(() -> new NoSuchElementException("Nije pronaden entity"));
    }

    @Test
    @Transactional
    public void updateOrder() {
        List<UserDto> allUsers = userJpa.getAllUsers();
        System.out.println(allUsers.toString());
        // Init data
        OrderDto initOrder = createInitOrder();

        // Check if not exists yet
        Assert.assertFalse(ordersRepository.findByCode(CODE)
                                           .isPresent());

        // Check if values from init are present
        Optional<OrdersEntity> entityFromInit = ordersRepository.findByCode(INIT_CODE);
        Assert.assertTrue(entityFromInit.isPresent());
        OrdersEntity ordersEntity = entityFromInit.get();

        // Assert if everything is as in import script
        Assert.assertEquals(true, ordersEntity.getActive());
        Assert.assertEquals(INIT_CASH_ON_DELIVERY, ordersEntity.getCashOnDelivery());
        Assert.assertEquals(INIT_DELIVERED, ordersEntity.getDelivered());
        Assert.assertEquals(INIT_ADDITIONAL_INFO, ordersEntity.getAdditionalInfo());
        Assert.assertEquals(INIT_ADDRESS, ordersEntity.getAddress());
        Assert.assertEquals(INIT_DELIVERY_DATE_TIME, ordersEntity.getDeliveryDateTime());
        Assert.assertEquals(INIT_CREATED_TIME, ordersEntity.getCreatedTime());
        Assert.assertEquals(INIT_PRICE, ordersEntity.getPrice());

        // Change information from import script to information from initOrder method by changing code
        initOrder.setCode(INIT_CODE);

        // Execute update
        orderController.updateOrder(initOrder);

        OrdersEntity updatedOrder = findByCode(INIT_CODE);

        Assert.assertEquals(true, updatedOrder.getActive());
        Assert.assertEquals(CASH_ON_DELIVERY, updatedOrder.getCashOnDelivery());
        Assert.assertEquals(DELIVERED, updatedOrder.getDelivered());
        Assert.assertEquals(ADDITIONAL_INFO, updatedOrder.getAdditionalInfo());
        Assert.assertEquals(ADDRESS, updatedOrder.getAddress());
        Assert.assertEquals(DELIVERY_DATE_TIME, updatedOrder.getDeliveryDateTime());
        Assert.assertEquals(CREATED_TIME, updatedOrder.getCreatedTime());
        Assert.assertEquals(PRICE, updatedOrder.getPrice());
    }

    @Test
    @Transactional
    public void deactivateOrder() {
        OrdersEntity orderByCode = findByCode(INIT_CODE);

        Assert.assertTrue(orderByCode.getActive());

        orderController.deactivateOrder(INIT_CODE);

        orderByCode = findByCode(INIT_CODE);

        Assert.assertFalse(orderByCode.getActive());
    }
}