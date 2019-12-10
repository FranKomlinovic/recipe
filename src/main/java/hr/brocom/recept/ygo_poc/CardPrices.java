package hr.brocom.recept.ygo_poc;

import com.google.gson.annotations.SerializedName;

public class CardPrices {
    @SerializedName("cardmarket_price")
    private String cardmarketPrice;
    @SerializedName("tcgplayer_price")
    private String tcgplayerPrice;
    @SerializedName("ebay_price")
    private String ebayPrice;
    @SerializedName("amazon_price")
    private String amazonPrice;

    public String getCardmarketPrice() {
        return cardmarketPrice;
    }

    public void setCardmarketPrice(String cardmarketPrice) {
        this.cardmarketPrice = cardmarketPrice;
    }

    public String getTcgplayerPrice() {
        return tcgplayerPrice;
    }

    public void setTcgplayerPrice(String tcgplayerPrice) {
        this.tcgplayerPrice = tcgplayerPrice;
    }

    public String getEbayPrice() {
        return ebayPrice;
    }

    public void setEbayPrice(String ebayPrice) {
        this.ebayPrice = ebayPrice;
    }

    public String getAmazonPrice() {
        return amazonPrice;
    }

    public void setAmazonPrice(String amazonPrice) {
        this.amazonPrice = amazonPrice;
    }
}
