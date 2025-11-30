package com.example.collectorsgarage.collectorGarage.coches;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.collectorsgarage.databinding.ActivityMainScreenBinding;


public class PaginaPrincipalActivity extends AppCompatActivity {

    private ActivityMainScreenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        binding = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("");

        TextView empezar = binding.start;


        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToMarcasActivity();
            }
        });

    }

    private void navigateToMarcasActivity() {
        Intent intent = new Intent(this, VehiculoActivity.class);
        startActivity(intent);
    }

}
