package com.example.clase06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.example.clase06.ViewModels.ContadorViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        final ContadorViewModel contadorViewModel = viewModelProvider.get(ContadorViewModel.class);
        contadorViewModel.iniciarContador2();
/*
        contadorViewModel.getContador().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 21) {
                    contadorViewModel.getContador().setValue(1);
                }
                TextView textView = findViewById(R.id.textView);
                textView.setText(String.valueOf(integer));
            }
        });*/

        contadorViewModel.getContador2().observe(this, new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> booleanIntegerPair) {

                if (booleanIntegerPair.first){
                    int valor = booleanIntegerPair.second;
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(String.valueOf(valor));
                }

            }
        });
    }

    public void iniciarContador(View view) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        ContadorViewModel contadorViewModel = viewModelProvider.get(ContadorViewModel.class);
        contadorViewModel.iniciarContador2();

    }

    public void detenerContador(View view) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        ContadorViewModel contadorViewModel = viewModelProvider.get(ContadorViewModel.class);
        contadorViewModel.detenerContador();
    }
}