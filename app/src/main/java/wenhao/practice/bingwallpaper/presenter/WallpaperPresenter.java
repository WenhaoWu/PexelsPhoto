package wenhao.practice.bingwallpaper.presenter;

import io.reactivex.disposables.Disposable;
import wenhao.practice.bingwallpaper.action.WallpaperAction;
import wenhao.practice.bingwallpaper.contract.WallpaperContract;
import wenhao.practice.bingwallpaper.infra.BasePresenter;
import wenhao.practice.bingwallpaper.util.RxUtils;

public class WallpaperPresenter extends BasePresenter<WallpaperContract> {

    public void fetchWallpaper(int count) {
        Disposable mSubscription;

        mSubscription = WallpaperAction.INSTANCE
                .fetch(mRetrofit, count)
                .compose(RxUtils.thread(mView))
                .subscribe(
                        wallpapers -> mView.onWallpaperFetch(wallpapers),
                        error()
                );

        mCompositeDisposable.add(mSubscription);
    }
}
