package com.example.collectorsgarage.collectorGarage.coches;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.collectorsgarage.databinding.ActivityVehiculoBinding;

public class VehiculoActivity extends AppCompatActivity {

    private ActivityVehiculoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        binding = ActivityVehiculoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("");

        TextView cars = binding.cars;


        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToMarcasActivity();
            }
        });

    }

    private void navigateToMarcasActivity() {
        Intent intent = new Intent(this, MarcasActivity.class);
        startActivity(intent);
    }

}
