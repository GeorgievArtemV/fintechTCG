package com.example.fintechtcg.ui.screen;

import com.example.fintechtcg.model.Film;
import com.example.fintechtcg.model.FilmDetail;
import com.example.fintechtcg.model.ListFilms;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmAPI {
    @Headers({"X-API-KEY:" + " e30ffed0-76ab-4dd6-b41f-4c9da2b2735b", "accept: application/json"})
    @GET("api/v2.2/films/collections?type=TOP_250_MOVIES&page=1")
    Call<ListFilms> getFilms();
    @Headers({"X-API-KEY:" + " e30ffed0-76ab-4dd6-b41f-4c9da2b2735b", "accept: application/json"})
    @GET("api/v2.2/films/{id}")
    Call<FilmDetail> getFilmDetails(@Path("id") int filmId);
}
