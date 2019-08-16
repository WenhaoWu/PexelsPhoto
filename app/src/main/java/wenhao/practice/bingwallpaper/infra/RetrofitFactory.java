package wenhao.practice.bingwallpaper.infra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitFactory {

    private static final int TIME_OUT = 20;

    private static volatile RetrofitFactory instance = null;
    private OkHttpClient client;

    private RetrofitFactory() {

        HttpLoggingInterceptor httpAgent = new HttpLoggingInterceptor();
        httpAgent.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(httpAgent);

        client = builder.build();
    }

    static RetrofitFactory getInstance() {

        RetrofitFactory localInstance = instance;

        if (localInstance == null) {
            Class clazz = RetrofitFactory.class;
            synchronized (clazz) {
                instance = new RetrofitFactory();
            }
        }
        return instance;
    }

    Retrofit getRetrofit() {

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
}
