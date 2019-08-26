package me.awesome.photo.presenter;

import io.reactivex.disposables.Disposable;
import me.awesome.photo.action.PhotoAction;
import me.awesome.photo.contract.PhotoContract;
import me.awesome.photo.infra.BasePresenter;
import me.awesome.photo.util.RxUtils;

public class PhotoPresenter extends BasePresenter<PhotoContract> {

    public void fetchWallpaper(int count) {

        if (mLoading) return;

        Disposable mSubscription;

        mSubscription = PhotoAction.INSTANCE
                .fetch(mRetrofit, count)
                .compose(RxUtils.thread(mView))
                .compose(loading())
                .subscribe(
                        wallpapers -> mView.onWallpaperFetch(wallpapers),
                        error()
                );

        mCompositeDisposable.add(mSubscription);
    }
}
