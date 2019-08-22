package me.awesome.wallpaper.util;

import android.app.AlertDialog;
import android.content.Context;

import java.lang.ref.WeakReference;

import me.awesome.wallpaper.R;

public class DialogCreator {

    private static volatile DialogCreator mInstance = null;
    private WeakReference<Context> mContextRef;

    private DialogCreator(Context mContext) {
        this.mContextRef = new WeakReference<Context>(mContext);
    }

    public static DialogCreator getInstance(Context context) {
        if (mInstance == null || !context.getApplicationContext().equals(mInstance.mContextRef.get())) {
            Class clazz = DialogCreator.class;
            synchronized (clazz) {
                mInstance = new DialogCreator(context);
            }
        }
        return mInstance;
    }

    public void showGeneralErrorDialog() {
        Context mContext = mContextRef.get();

        buildBoringDialog(
                mContext.getString(R.string.general_error_title),
                mContext.getString(R.string.general_error_message)
        );
    }

    private void buildBoringDialog(CharSequence title, CharSequence msg) {

        Context mContext = mContextRef.get();

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, (dialog1, which) -> {

                })
                .show();
    }
}
