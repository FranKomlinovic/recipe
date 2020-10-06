package hr.brocom.generic.entity;

import hr.brocom.generic.abstraction.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@ToString
@Entity
public class User extends BaseEntity {
    private String email;
    private LocalDate birthday;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String address;
}
