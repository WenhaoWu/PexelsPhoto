package me.awesome.photo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.Objects;

import me.awesome.photo.R;

public enum ProgressDialogBuilder {
    ;

    @Nullable
    public static ProgressDialog createProgressDialog(Context contextSource) {

        Context context = new WeakReference<>(contextSource).get();

        if (context == null) return null;

        ProgressDialog dialog = new ProgressDialog(context);
        try {
            dialog.show();
            dialog.setCancelable(true);
            Objects.requireNonNull(dialog.getWindow())
                    .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(R.layout.progress_dialog_layout);
        } catch (WindowManager.BadTokenException e) {
            return null;
        } finally {
            dialog.dismiss();
        }

        return dialog;
    }

}
