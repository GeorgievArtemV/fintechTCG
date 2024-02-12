package com.example.fintechtcg.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.example.fintechtcg.R;
import com.example.fintechtcg.databinding.ActivityMainBinding;
import com.example.fintechtcg.ui.screen.Core;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportFragmentManager().beginTransaction().replace(R.id.placeHolder, new Core()).commit();
        //кэширование (база данных), нажатие на элемент с открытием деталей, проверка интернета, шиммеры,ориентация, юнит тесты, переворот экрана

    }


}
