package hr.brocom.generic.abstraction.ping;

import hr.brocom.generic.abstraction.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@ToString
@Entity
public class Ping extends BaseEntity {
    private String message;
}
