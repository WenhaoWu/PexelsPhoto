package wenhao.practice.bingwallpaper.infra;

import android.content.Context;

public interface BaseContract {
    void onTaskStart();

    void onTaskError(Throwable throwable);

    void onTaskComplete();

    Context ensureContext();
}
