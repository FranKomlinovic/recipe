package hr.brocom.recept.ygo_poc;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("desc")
    private String desc;
    @SerializedName("race")
    private String race;
    @SerializedName("archetype")
    private String archetype;
    @SerializedName("card_sets")
    private List<CardSet> cardSets = null;
    @SerializedName("banlist_info")
    private BanlistInfo banlistInfo;
    @SerializedName("card_images")
    private List<CardImage> cardImages = null;
    @SerializedName("card_prices")
    private CardPrices cardPrices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<CardSet> getCardSets() {
        return cardSets;
    }

    public void setCardSets(List<CardSet> cardSets) {
        this.cardSets = cardSets;
    }

    public BanlistInfo getBanlistInfo() {
        return banlistInfo;
    }

    public void setBanlistInfo(BanlistInfo banlistInfo) {
        this.banlistInfo = banlistInfo;
    }

    public List<CardImage> getCardImages() {
        return cardImages;
    }

    public void setCardImages(List<CardImage> cardImages) {
        this.cardImages = cardImages;
    }

    public CardPrices getCardPrices() {
        return cardPrices;
    }

    public void setCardPrices(CardPrices cardPrices) {
        this.cardPrices = cardPrices;
    }
}
