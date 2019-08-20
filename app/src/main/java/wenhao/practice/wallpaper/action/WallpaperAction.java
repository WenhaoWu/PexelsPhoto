package wenhao.practice.wallpaper.action;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import wenhao.practice.wallpaper.api.WallpaperService;
import wenhao.practice.wallpaper.model.Wallpaper;
import wenhao.practice.wallpaper.model.WallpaperResponse;

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
