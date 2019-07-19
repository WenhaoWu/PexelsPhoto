package wenhao.practice.bingwallpaper.action;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import wenhao.practice.bingwallpaper.api.WallpaperService;
import wenhao.practice.bingwallpaper.model.Image;
import wenhao.practice.bingwallpaper.model.WallpaperResponse;

public enum WallpaperAction {
    INSTANCE;

    private static final int IMAGE_PER_PAGE = 8;

    public Single<List<Image>> fetch(Retrofit retrofit, int currentCount) {
        return Single.just(0)
                .map(integer -> currentCount == 0
                        ? 0
                        : (int) Math.ceil(currentCount / (double) IMAGE_PER_PAGE))
                .map(page -> page + 1)
                .flatMap(page -> retrofit.create(WallpaperService.class)
                        .fetchWallpapers(IMAGE_PER_PAGE, page)
                        .firstOrError()
                )
                .map(WallpaperResponse::getImages);
    }
}
