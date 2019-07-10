package hr.brocom.recept.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private String                code;
    private DelivererDto          deliverer;
    private UserDto               user;
    private LocalDateTime         createdTime;
    private LocalDateTime         deliveryDateTime;
    private String                address;
    private BigDecimal            price;
    private Boolean               cashOnDelivery;
    private Boolean               delivered;
    private String                additionalInfo;
    private List<OrderProductDto> orderProductList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DelivererDto getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(DelivererDto deliverer) {
        this.deliverer = deliverer;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(LocalDateTime deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(Boolean cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<OrderProductDto> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProductDto> orderProductList) {
        this.orderProductList = orderProductList;
    }

    @Override
    public String toString() {
        return "OrderDto{" + "code='" + code + '\'' + ", deliverer=" + deliverer + ", user=" + user + ", createdTime=" + createdTime + ", deliveryDateTime=" + deliveryDateTime + ", address='" + address + '\'' + ", price=" + price + ", cashOnDelivery=" + cashOnDelivery + ", delivered=" + delivered + ", additionalInfo='" + additionalInfo + '\'' + ", orderProductList=" + orderProductList + '}';
    }
}

