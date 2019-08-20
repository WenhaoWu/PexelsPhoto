package wenhao.practice.bingwallpaper.action;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import wenhao.practice.bingwallpaper.api.WallpaperService;
import wenhao.practice.bingwallpaper.model.Wallpaper;
import wenhao.practice.bingwallpaper.model.WallpaperResponse;

public enum WallpaperAction {
    INSTANCE;

    private static final int IMAGE_PER_PAGE = 6;

    public Single<List<Wallpaper>> fetch(Retrofit retrofit, int count) {
        return retrofit.create(WallpaperService.class)
                .fetchWallpapers("js", IMAGE_PER_PAGE, count == 0 ? count : count + 1)
                .firstOrError()
                .map(WallpaperResponse::getWallpapers);
    }
}
