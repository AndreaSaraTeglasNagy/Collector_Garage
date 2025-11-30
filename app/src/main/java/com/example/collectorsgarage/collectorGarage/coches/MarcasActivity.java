package com.example.collectorsgarage.collectorGarage.coches;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.collectorsgarage.R;
import com.example.collectorsgarage.collectorGarage.GenericUtils;
import com.example.collectorsgarage.collectorGarage.adapters.MarcaAdapter;
import com.example.collectorsgarage.collectorGarage.database.CochesDatabaseManager;
import com.example.collectorsgarage.collectorGarage.objetos.Marca;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MarcasActivity extends AppCompatActivity {

    private com.example.collectorsgarage.databinding.ActivityMarcasBinding binding;
    private com.example.collectorsgarage.collectorGarage.adapters.MarcaAdapter marcasAdapter;
    private List<Marca> marcas;


    /**
     *  Inicia la aplicación y recoge la información que quieres enseñar.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.collectorsgarage.databinding.ActivityMarcasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle(com.example.collectorsgarage.R.string.brands);
        int width = binding.widthMarca.getWidth();
        loadDataFromDatabase();
        initializeRecyclerView();
        initializeFiltro();
    }

    private void loadDataFromDatabase() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            marcas = CochesDatabaseManager.getInstance(this).getAllMarcasBasicData();
            handler.post(() -> {
                binding.progressBar.setVisibility(View.INVISIBLE);
                binding.recyclerView.setVisibility(View.VISIBLE);
                marcasAdapter.refreshAdapter(marcas);
            });
        });
    }

    private void initializeFiltro() {

        binding.marcaFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filtrarMarcas(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable charSequence) {

            }
        });
    }

    /**
     *  se encarga de configurar y mostrar la lista de marcas en la aplicación, asegurándose de que esté
     *  lista para ser vista y de que pueda responder a las interacciones del usuario.
     */

    private void initializeRecyclerView() {
        marcasAdapter = new MarcaAdapter(this, new MarcaAdapter.OnMarcaClickedListener() {
            @Override
            public void onClick(Marca marca) {
                navigateToModelosActivity(marca);
            }
        });
        instantiateAndSetupLayoutManager(binding.recyclerView);
        binding.recyclerView.setAdapter(marcasAdapter);
    }

    /**
     * indicas el numero de columnas que quieres que tenga, como el grid de css.
     */

    private void instantiateAndSetupLayoutManager(RecyclerView recyclerView) {
        float width = Resources.getSystem().getDisplayMetrics().widthPixels;
        float itemWidth = getResources().getDimension(R.dimen.list_item_marca_width);
        int numColumns =  (int) (width / itemWidth);
        GridLayoutManager layoutManager = new GridLayoutManager(this, numColumns);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * que te lleve a la página de ModelosActivity.
     */

    private void navigateToModelosActivity(Marca marca) {
        Intent intent = new Intent(this, ModelosActivity.class);
        String json = GenericUtils.serialize(marca);
        intent.putExtra("marca", json);
        startActivity(intent);
    }

    private void filtrarMarcas(String inputnombreMarca) {
        List<Marca> marcasFiltradas = new ArrayList<>();
        for (Marca marca : marcas) {
            String nombreModelo = marca.getNombre().toLowerCase();
            if (nombreModelo.contains(inputnombreMarca.toLowerCase())) {
                marcasFiltradas.add(marca);
            }
        }
        marcasAdapter.refreshAdapter(marcasFiltradas);
    }
}