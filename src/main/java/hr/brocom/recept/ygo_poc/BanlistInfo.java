package hr.brocom.recept.ygo_poc;

import com.google.gson.annotations.SerializedName;

public class BanlistInfo {
    @SerializedName("ban_goat")
    private String banGoat;

    public String getBanGoat() {
        return banGoat;
    }

    public void setBanGoat(String banGoat) {
        this.banGoat = banGoat;
    }
}
