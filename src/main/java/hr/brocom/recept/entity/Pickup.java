package hr.brocom.recept.entity;

import hr.brocom.recept.abstraction.entity.BaseEntity;
import hr.brocom.recept.enums.CapMaterial;
import hr.brocom.recept.enums.ConnectionType;
import hr.brocom.recept.enums.IsolationType;
import hr.brocom.recept.enums.MagnetType;
import hr.brocom.recept.enums.MangeticDirection;
import hr.brocom.recept.enums.Output;
import hr.brocom.recept.enums.PickupCarrierType;
import hr.brocom.recept.enums.PickupType;
import hr.brocom.recept.enums.Position;
import hr.brocom.recept.enums.PotType;
import hr.brocom.recept.enums.WindingDirection;
import hr.brocom.recept.enums.WindingMethod;
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
    private String name;
    private Integer year;
    private PickupType type;
    private boolean active;
    private Integer stringNumber;
    @ManyToOne
    private NamjenaPickupa namjenaPickupa;
    @ManyToOne
    private InstrumentType instrumentType;
    private Position position;
    private Double resistance;
    private Double coilTapAt;
    private Double inductivity;
    private Double outputInMilivolts;
    private MangeticDirection magneticDirection;
    private int magnetQuantity;
//    private MagnetType magnetType;
//    // Smisli nesto pametno tu
//    private MagnetShape magnetShape;
//    private MagnetDim magnetDim;
//    private MagnetMake magnetMake;
    // Ovo je ista stvar samo preracunaj
    private int gauss;
    private int tesla;
    private Integer numberOfFirstCoilWinding;
    private Integer numberOfSecondCoilWinding;
    private WindingDirection windingDirection;
    @ManyToOne
    private WireType wireType;
    private IsolationType isolationType;
    private PickupCarrierType pickupCarrierType;
    private ConnectionType connectionType;
    private boolean separable;
    private Integer numberOfOutputWires;
    private boolean adjustableCore;
    // HIGH MEDIUM LOW
    private Output output;
    private boolean balanced;
    private WindingMethod windingMethod;
    private boolean potan;
    private PotType potType;
    private CapMaterial capMaterial;
    // vezani su ovaj i sljedeci
//    private InformationSource source;
//    private Integer sourceMark;
//    private String webLink;
//    private String autocadLink;
    private String description;
//    private Photo slika;



}
