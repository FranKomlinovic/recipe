package hr.brocom.recept.domain.jpa.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "deliverer")
public class DelivererEntity {
    private Long               id;
    private String             code;
    private String             first_name;
    private String             last_name;
    private Boolean            active = true;
    private List<OrdersEntity> orderList;

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
    @Column(name = "first_name", nullable = false)
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Basic
    @Column(name = "last_name", nullable = false)
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "deliverer")
    public List<OrdersEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrdersEntity> ordersList) {
        this.orderList = ordersList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, first_name, last_name, active);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DelivererEntity that = (DelivererEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(first_name,
                                                                                                that.first_name) && Objects.equals(
                last_name,
                that.last_name) && Objects.equals(active, that.active);
    }
}

