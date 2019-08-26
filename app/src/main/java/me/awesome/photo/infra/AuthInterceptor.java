package me.awesome.photo.infra;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import me.awesome.photo.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AuthInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                .header("Authorization", BuildConfig.PEXELS_API_TOKEN)
                .header("Accept", "application/json")
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}
