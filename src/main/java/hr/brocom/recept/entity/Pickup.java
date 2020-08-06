package hr.brocom.recept.entity;

import hr.brocom.recept.abstraction.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@ToString
@Entity
public class Pickup extends BaseEntity {

    @ManyToOne
    private Manufacturer manufacturer;
//    private String name;
//    private Integer year;
//    private PickupType type;
//    private boolean active;
//    private PickupDesign design;
//    private NamjenaPickupa namjenaPickupa;
//    private InstrumentType instrumentType;
//    private Position position;
//    private Double resistance;
//    private Double coilTapAt;
//    private Double inductivity;
//    private Double output;
//    private MangeticDirection magneticDirection;
//    private MagnetType magnetType;
//    private int magnetQuantity;
//    private MagnetShape magnetShape;
//    private MagnetDim magnetDim;
//    private MagnetMake magnetMake;
//    private Gauss gauss;
//    private Tesla tesla;
//    private Integer numberOfFirstCoilWinding;
//    private Integer numberOfSecondCoilWinding;
//    private WindingDirection windingDirection;
//    private WireType wireType;
//    private IsolationType isolationType;
//    private PickupCarrierType pickupCarrierType;
//    private WindingProtectionType windingProtectionType;
//    private boolean separable;
//    private ConnectionType connectionType;
//    private Integer numberOfOutputWires;
//    private boolean adjustableCore;
//    private Output output;
//    private boolean balanced;
//    private WindingMethod windingMethod;
//    private boolean potan;
//    private PotType potType;
//    private CapMaterial capMaterial;
//    private CapColor capColor;
//    private InformationSource source;
//    private Integer sourceMark;
//    private String webLink;
//    private String autocadLink;
//    private String description;


}
