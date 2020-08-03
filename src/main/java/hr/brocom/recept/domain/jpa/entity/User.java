package hr.brocom.recept.domain.jpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@ToString
@Entity
public class User extends BaseEntity{
    private String email;
    private LocalDate birthday;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String address;
}
