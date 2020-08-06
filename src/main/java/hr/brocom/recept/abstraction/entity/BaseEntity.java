package hr.brocom.recept.abstraction.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@ToString
@Entity
public class BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;

    private Boolean active = true;

    protected BaseEntity() {
        // Empty constructor used to avoid instancing of BaseEntity object
    }
}

