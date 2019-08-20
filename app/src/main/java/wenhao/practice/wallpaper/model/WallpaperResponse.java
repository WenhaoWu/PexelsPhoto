package wenhao.practice.wallpaper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WallpaperResponse {
    @SerializedName("images")
    @Expose
    private List<Wallpaper> wallpapers;

    public List<Wallpaper> getWallpapers() {
        return wallpapers;
    }
}
