package wenhao.practice.bingwallpaper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WallpaperResponse {
    @SerializedName("images")
    @Expose
    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }
}
