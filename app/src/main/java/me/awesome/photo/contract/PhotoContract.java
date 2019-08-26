package me.awesome.photo.contract;

import java.util.List;

import me.awesome.photo.infra.BaseContract;
import me.awesome.photo.model.Photo;

public interface PhotoContract extends BaseContract {
    void onWallpaperFetch(List<Photo> photos);
}
