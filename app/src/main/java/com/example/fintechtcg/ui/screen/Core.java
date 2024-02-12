package com.example.fintechtcg.ui.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MyApp;
import com.example.fintechtcg.R;
import com.example.fintechtcg.databinding.FragmentCoreBinding;
import com.example.fintechtcg.model.Film;
import com.example.fintechtcg.model.ListFilms;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Core extends Fragment implements OnItemClick,OnLongItemClick  {

    private FragmentCoreBinding binding;
    private FilmAdapter filmAdapter;


    List<String> listNameFilm = new ArrayList<>();
    List<String> listURL = new ArrayList<>();
    List<String> listYEARS = new ArrayList<>();
    List<Film> list = new ArrayList<>();
    int countPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCoreBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        RecyclerView recyclerView = binding.filmlist;
        Call<ListFilms> call = MyApp.filmAPI.getFilms();
        call.enqueue(new Callback<ListFilms>() {
            @Override
            public void onResponse(Call<ListFilms> call, Response<ListFilms> response) {

                list.addAll(response.body().getList());

                filmAdapter = new FilmAdapter(list, Core.this, Core.this);
                recyclerView.setAdapter(filmAdapter);
                binding.loadingProgressBar.setVisibility(View.INVISIBLE);
                binding.filmlist.setVisibility(View.VISIBLE);
                binding.layoutNoInternet.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ListFilms> call, Throwable t) {      //кэширование, обработка изменения ориентации
                Log.e("TAG", t.getMessage());
                binding.loadingProgressBar.setVisibility(View.INVISIBLE);
                binding.layoutNoInternet.setVisibility(View.VISIBLE);
                call.clone().enqueue(this);
            }
        });
        
        binding.poisk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }


    @Override
    public void onItemClick(int item) {
        Bundle bundle = new Bundle();
        bundle.putInt("1", list.get(item).getKinopoiskId());
        Details details = new Details();
        details.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.placeHolder, details).addToBackStack("qwe").commit();
    }

    @Override
    public void onLongItemClick(int item) {
        Toast.makeText(getContext(), "Фильм добавлен в избранное", Toast.LENGTH_LONG).show();
    }
   /* private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }*/
}