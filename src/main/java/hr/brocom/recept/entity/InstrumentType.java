package hr.brocom.recept.entity;

import hr.brocom.recept.abstraction.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@ToString
@Entity
public class InstrumentType extends BaseEntity {
    private String name;
}
