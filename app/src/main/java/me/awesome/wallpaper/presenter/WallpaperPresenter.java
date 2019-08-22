package me.awesome.wallpaper.presenter;

import io.reactivex.disposables.Disposable;
import me.awesome.wallpaper.action.WallpaperAction;
import me.awesome.wallpaper.contract.WallpaperContract;
import me.awesome.wallpaper.infra.BasePresenter;
import me.awesome.wallpaper.util.RxUtils;

public class WallpaperPresenter extends BasePresenter<WallpaperContract> {

    public void fetchWallpaper(int count) {

        if (mLoading) return;

        Disposable mSubscription;

        mSubscription = WallpaperAction.INSTANCE
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
