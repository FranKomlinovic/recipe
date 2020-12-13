package hr.brocom.generic.abstraction.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@ToString
@MappedSuperclass
@Data
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;

    private Boolean active = true;

    /**
     * Constructor.
     */
    protected BaseEntity() {
        // Empty constructor so class can be extended by entities.
    }
}

