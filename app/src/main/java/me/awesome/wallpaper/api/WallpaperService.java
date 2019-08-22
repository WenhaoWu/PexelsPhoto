package me.awesome.wallpaper.api;

import io.reactivex.Flowable;
import me.awesome.wallpaper.model.WallpaperResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WallpaperService {

    @GET("/HPImageArchive.aspx")
    Flowable<WallpaperResponse> fetchWallpapers(
            @Query("format") String format,
            @Query("n") int count,
            @Query("idx") int page
    );

}
