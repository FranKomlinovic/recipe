package hr.brocom.recept.ygo_poc;

public class CardEntity {
    private Long id;
    private String code;
    private String name;
    private String type;
    private String desc;
    private String race;
    private String archetype;
    private String setName;
    private String setRarity;
    private String price;
    private String banlist;
    private String cardImage;
    private String cardImageSmall;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetRarity() {
        return setRarity;
    }

    public void setSetRarity(String setRarity) {
        this.setRarity = setRarity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBanlist() {
        return banlist;
    }

    public void setBanlist(String banlist) {
        this.banlist = banlist;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public String getCardImageSmall() {
        return cardImageSmall;
    }

    public void setCardImageSmall(String cardImageSmall) {
        this.cardImageSmall = cardImageSmall;
    }
}
