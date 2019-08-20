package wenhao.practice.wallpaper.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wenhao.practice.wallpaper.model.WallpaperResponse;

public interface WallpaperService {

    @GET("/HPImageArchive.aspx")
    Flowable<WallpaperResponse> fetchWallpapers(
            @Query("format") String format,
            @Query("n") int count,
            @Query("idx") int page
    );

}
