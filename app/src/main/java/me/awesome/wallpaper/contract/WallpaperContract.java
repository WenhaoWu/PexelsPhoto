package me.awesome.wallpaper.contract;

import java.util.List;

import me.awesome.wallpaper.infra.BaseContract;
import me.awesome.wallpaper.model.Wallpaper;

public interface WallpaperContract extends BaseContract {
    void onWallpaperFetch(List<Wallpaper> wallpapers);
}
