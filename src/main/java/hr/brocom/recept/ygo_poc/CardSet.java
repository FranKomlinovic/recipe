package hr.brocom.recept.ygo_poc;

import com.google.gson.annotations.SerializedName;

public class CardSet {
    @SerializedName("set_name")
    private String setName;
    @SerializedName("set_code")
    private String setCode;
    @SerializedName("set_rarity")
    private String setRarity;
    @SerializedName("set_price")
    private String setPrice;

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getSetRarity() {
        return setRarity;
    }

    public void setSetRarity(String setRarity) {
        this.setRarity = setRarity;
    }

    public String getSetPrice() {
        return setPrice;
    }

    public void setSetPrice(String setPrice) {
        this.setPrice = setPrice;
    }
}
