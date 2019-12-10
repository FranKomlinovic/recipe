package hr.brocom.recept.ygo_poc;

import com.google.gson.annotations.SerializedName;

public class Set {
    @SerializedName("Set Name")
    private String setName;

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }
}
