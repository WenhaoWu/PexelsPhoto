package me.awesome.photo.infra;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import me.awesome.photo.util.DialogCreator;
import me.awesome.photo.util.ProgressDialogBuilder;

public abstract class BaseView<V extends BaseContract, T extends BasePresenter<V>>
        extends AppCompatActivity
        implements BaseContract {

    private ProgressDialog mProgress;

    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();

        //noinspection unchecked
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) mPresenter.detachView();

        this.onTaskComplete();

        super.onDestroy();
    }

    protected boolean isLoading() {
        return mProgress != null && mProgress.isShowing();
    }

    @Override
    public void onTaskStart() {

        if (mProgress == null)
            mProgress = ProgressDialogBuilder.createProgressDialog(this);

        if (mProgress != null)
            mProgress.show();

    }

    @Override
    public void onTaskError(Throwable throwable) {

        if (ensureContext() == null)
            return;

        if (isLoading())
            mProgress.dismiss();

        DialogCreator dialog = DialogCreator.getInstance(ensureContext());

        dialog.showGeneralErrorDialog();
    }

    @Override
    public void onTaskComplete() {
        if (isLoading())
            mProgress.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.refresh();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null)
            mPresenter.unSubscribe();
        this.onTaskComplete();
    }

    @Override
    public Context ensureContext() {
        return this;
    }

    protected abstract T createPresenter();

}
