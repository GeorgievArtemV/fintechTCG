package com.example;

import android.app.Application;

import com.example.fintechtcg.ui.screen.FilmAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    public static FilmAPI filmAPI;
    private HttpLoggingInterceptor interceptor;
    private OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient();
        client.newBuilder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kinopoiskapiunofficial.tech/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        filmAPI = retrofit.create(FilmAPI.class);
    }
}
