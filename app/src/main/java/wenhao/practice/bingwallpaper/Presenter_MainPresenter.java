package wenhao.practice.bingwallpaper;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by wenhaowu on 28/12/15.
 */
public class Presenter_MainPresenter {
    private Context mContext;

    private ArrayList<Object_Wallpaper> mWallpapers = new ArrayList<>();
    private Throwable mError =null;

    private View_MainActivity view;

    public Presenter_MainPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void prepareItem (final int idx){
        Model_GetImages.getImages(new Model_GetImages.volleyCallback() {
            @Override
            public void onSuccess(ArrayList<Object_Wallpaper> items) {
                mWallpapers = items;
                publish();
            }

            @Override
            public void onFail(Throwable error) {
                //mAdapter.clearAll();
                mError = error;
                publish();
            }
        }, idx, mContext);
    }

    public void onTakeView(View_MainActivity view){
        this.view = view;
        publish();
    }

    private void publish() {
        if (view != null) {
            if (!mWallpapers.isEmpty())
                view.onItemsNext(mWallpapers);
            else if (mError != null)
                view.onItemsError(mError);
        }
    }

}
