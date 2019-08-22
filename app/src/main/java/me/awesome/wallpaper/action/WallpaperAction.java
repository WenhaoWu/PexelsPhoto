package me.awesome.wallpaper.action;

import java.util.List;

import io.reactivex.Single;
import me.awesome.wallpaper.api.WallpaperService;
import me.awesome.wallpaper.model.Wallpaper;
import me.awesome.wallpaper.model.WallpaperResponse;
import retrofit2.Retrofit;

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
