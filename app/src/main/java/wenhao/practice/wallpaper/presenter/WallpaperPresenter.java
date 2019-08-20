package wenhao.practice.wallpaper.presenter;

import io.reactivex.disposables.Disposable;
import wenhao.practice.wallpaper.action.WallpaperAction;
import wenhao.practice.wallpaper.contract.WallpaperContract;
import wenhao.practice.wallpaper.infra.BasePresenter;
import wenhao.practice.wallpaper.util.RxUtils;

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
