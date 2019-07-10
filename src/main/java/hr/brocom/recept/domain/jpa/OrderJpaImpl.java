package hr.brocom.recept.domain.jpa;

import hr.brocom.recept.domain.jpa.entity.DelivererEntity;
import hr.brocom.recept.domain.jpa.entity.OrdersEntity;
import hr.brocom.recept.domain.jpa.entity.UserEntity;
import hr.brocom.recept.domain.jpa.repository.DelivererRepository;
import hr.brocom.recept.domain.jpa.repository.OrdersRepository;
import hr.brocom.recept.domain.jpa.repository.UserRepository;
import hr.brocom.recept.model.OrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class OrderJpaImpl {

    @Autowired
    OrdersRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DelivererRepository delivererRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public List<OrderDto> getAllActiveOrders() {
        return orderRepository.findAllByActiveTrue()
                              .stream()
                              .map(r -> modelMapper.map(r, OrderDto.class))
                              .collect(Collectors.toList());
    }

    @Transactional
    public void addOrder(OrderDto orderDto) {
        modifyOrder(new OrdersEntity(), orderDto);
    }

    private void modifyOrder(OrdersEntity orderEntity, OrderDto orderDto) {
        orderEntity.setCode(orderDto.getCode());

        //TODO iz repozitorija povuci to
        orderEntity.setDeliverer(findDelivererByCode(orderDto.getDeliverer()
                                                             .getCode()));
        orderEntity.setUser(findUserByUsername(orderDto.getUser()
                                                       .getNickname()));
        orderEntity.setCreatedTime(orderDto.getCreatedTime());
        orderEntity.setDeliveryDateTime(orderDto.getDeliveryDateTime());
        orderEntity.setAddress(orderDto.getAddress());
        orderEntity.setPrice(orderDto.getPrice());
        orderEntity.setCashOnDelivery(orderDto.getCashOnDelivery());
        orderEntity.setDelivered(orderDto.getDelivered());
        orderEntity.setAdditionalInfo(orderDto.getAdditionalInfo());

        orderRepository.saveAndFlush(orderEntity);
    }

    private DelivererEntity findDelivererByCode(String code) {
        return delivererRepository.findByCode(code)
                                  .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }

    private UserEntity findUserByUsername(String username) {
        return userRepository.findByNickname(username)
                             .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }

    @Transactional
    public void updateOrder(OrderDto orderDto) {
        OrdersEntity orderEntity = findByCode(orderDto.getCode());
        modifyOrder(orderEntity, orderDto);
    }

    private OrdersEntity findByCode(String code) {
        return orderRepository.findByCode(code)
                              .orElseThrow(() -> new NoSuchElementException("Nema entity-a"));
    }

    @Transactional
    public void deactivateOrder(String code) {
        OrdersEntity orderEntity = findByCode(code);
        orderEntity.setActive(false);
        orderRepository.saveAndFlush(orderEntity);
    }
}
