package wenhao.practice.bingwallpaper.api;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wenhao.practice.bingwallpaper.model.WallpaperResponse;

public interface WallpaperService {


    @GET("/HPImageArchive.aspx?format=js&n={count}&idx={page}")
    Flowable<WallpaperResponse> fetchWallpapers(
            @Path("count") int count,
            @Path("page") int page
    );

}
