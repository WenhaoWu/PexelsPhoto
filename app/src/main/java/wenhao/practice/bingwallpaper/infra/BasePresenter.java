package wenhao.practice.bingwallpaper.infra;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.function.Consumer;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

public abstract class BasePresenter<T extends BaseContract> {
    protected T mView;
    protected CompositeDisposable mCompositeDisposable;
    protected Retrofit mRetrofit;

    private Reference<T> mViewRef;

    private void init() {
        this.mCompositeDisposable = new CompositeDisposable();

        this.mRetrofit = RetrofitFactory
                .getInstance()
                .getRetrofit(Constant.API_BASE_URL);
    }

    void attachView(T view) {
        mViewRef = new WeakReference<>(view);

        if (isViewAttached()) {
            mView = getView();
            init();
        }
    }

    private T getView() {
        return mViewRef.get();
    }

    private boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    void detachView() {
        if (mViewRef != null) mViewRef.clear();
    }

    public void refresh() {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed())
            mCompositeDisposable = new CompositeDisposable();
    }

    public void unSubscribe() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed())
            mCompositeDisposable.dispose();
    }

    protected Consumer<Throwable> error() {
        return throwable -> mView.onTaskError(throwable);
    }

}