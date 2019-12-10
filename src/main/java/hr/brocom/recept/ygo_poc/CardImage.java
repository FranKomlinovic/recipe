package hr.brocom.recept.ygo_poc;

import com.google.gson.annotations.SerializedName;

public class CardImage {
    private String id;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("image_url_small")
    private String imageUrlSmall;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public void setImageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall;
    }
}
