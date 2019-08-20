package wenhao.practice.wallpaper.contract;

import java.util.List;

import wenhao.practice.wallpaper.infra.BaseContract;
import wenhao.practice.wallpaper.model.Wallpaper;

public interface WallpaperContract extends BaseContract {
    void onWallpaperFetch(List<Wallpaper> wallpapers);
}
