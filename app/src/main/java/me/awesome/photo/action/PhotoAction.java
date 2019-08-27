package me.awesome.photo.action;

import java.util.List;

import io.reactivex.Single;
import me.awesome.photo.api.PhotoService;
import me.awesome.photo.model.Photo;
import me.awesome.photo.model.PhotoResponse;
import retrofit2.Retrofit;

public enum PhotoAction {
    INSTANCE;

    private static final int IMAGE_PER_PAGE = 14;

    public Single<List<Photo>> fetch(Retrofit retrofit, int count) {

        int page = count == 0
                ? 1
                : (int) Math.ceil(count / (double) IMAGE_PER_PAGE) + 1;

        return retrofit.create(PhotoService.class)
                .fetchPhotos(IMAGE_PER_PAGE, page)
                .firstOrError()
                .map(PhotoResponse::getPhotos);
    }
}
