package hr.brocom.recept.domain.jpa.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class UserEntity {
    private Long id;
    private String username;
    private String mail;
    private String address;
    private Boolean active = true;
    private List<OrdersEntity> orderEntityList;

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
    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String nickname) {
        this.username = nickname;
    }

    @Basic
    @Column(name = "mail", nullable = false, unique = true)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "user")
    public List<OrdersEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrdersEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, mail, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(mail,
                that.mail) && Objects.equals(
                address,
                that.address);
    }
}
