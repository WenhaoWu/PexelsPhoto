package wenhao.practice.bingwallpaper.contract;

import java.util.List;

import wenhao.practice.bingwallpaper.infra.BaseContract;
import wenhao.practice.bingwallpaper.model.Wallpaper;

public interface WallpaperContract extends BaseContract {
    void onWallpaperFetch(List<Wallpaper> wallpapers);
}
