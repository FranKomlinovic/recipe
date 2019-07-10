package hr.brocom.recept.domain.jpa.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrdersEntity {
    private Long            id;
    private String          code;
    private DelivererEntity deliverer;
    private UserEntity      user;
    private LocalDateTime   createdTime;
    private LocalDateTime   deliveryDateTime;
    private String          address;
    private BigDecimal      price;
    private Boolean         cashOnDelivery;
    private Boolean         delivered = false;
    private String          additionalInfo;
    private Boolean         active    = true;

    private List<OrderProductEntity> orderProductList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = false, unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "created_time", nullable = false)
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "delivery_datetime")
    public LocalDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(LocalDateTime deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    @Basic
    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "price", precision = 15, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "cash_on_delivery", nullable = false)
    public Boolean getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(Boolean cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    @Basic
    @Column(name = "delivered", nullable = false)
    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    @Basic
    @Column(name = "additional_info")
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @ManyToOne
    @JoinColumn(name = "deliverer_id", referencedColumnName = "id", nullable = false)
    public DelivererEntity getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(DelivererEntity deliverer) {
        this.deliverer = deliverer;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    public List<OrderProductEntity> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProductEntity> orderProductList) {
        this.orderProductList = orderProductList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                            code,
                            deliverer,
                            user,
                            createdTime,
                            deliveryDateTime,
                            address,
                            price,
                            cashOnDelivery,
                            delivered,
                            additionalInfo,
                            active);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrdersEntity that = (OrdersEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(deliverer,
                                                                                                that.deliverer) && Objects.equals(
                user,
                that.user) && Objects.equals(createdTime, that.createdTime) && Objects.equals(deliveryDateTime,
                                                                                              that.deliveryDateTime) && Objects.equals(
                address,
                that.address) && Objects.equals(price, that.price) && Objects.equals(cashOnDelivery,
                                                                                     that.cashOnDelivery) && Objects.equals(
                delivered,
                that.delivered) && Objects.equals(additionalInfo, that.additionalInfo) && Objects.equals(active,
                                                                                                         that.active);
    }
}