package wenhao.practice.wallpaper.util;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import wenhao.practice.wallpaper.infra.BaseContract;

public enum RxUtils {
    ;

    public static <T> SingleTransformer<T, T> thread(BaseContract view) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .doOnSubscribe(s -> view.onTaskStart())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent((subscriptionResponse, throwable) -> view.onTaskComplete());
    }
}
