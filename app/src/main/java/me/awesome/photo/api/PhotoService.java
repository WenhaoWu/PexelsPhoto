package me.awesome.photo.api;

import io.reactivex.Flowable;
import me.awesome.photo.model.PhotoResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoService {

    @GET("/v1/curated")
    Flowable<PhotoResponse> fetchPhotos(
            @Query("per_page") int count,
            @Query("page") int page
    );

}
