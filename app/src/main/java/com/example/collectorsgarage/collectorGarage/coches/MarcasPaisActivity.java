package com.example.collectorsgarage.collectorGarage.coches;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collectorsgarage.R;
import com.example.collectorsgarage.collectorGarage.GenericUtils;
import com.example.collectorsgarage.collectorGarage.adapters.MarcaAdapter;
import com.example.collectorsgarage.collectorGarage.database.CochesDatabaseManager;
import com.example.collectorsgarage.collectorGarage.objetos.Marca;
import com.example.collectorsgarage.collectorGarage.objetos.Pais;
import com.example.collectorsgarage.databinding.ActivityMarcasBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MarcasPaisActivity extends AppCompatActivity {

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
        Intent intent = getIntent();
        String json = intent.getStringExtra("pais");
        Pais pais = com.example.collectorsgarage.collectorGarage.GenericUtils.deserialize(json, Pais.class);
        loadDataFromDatabase(pais);
        initializeRecyclerView();
    }

    private void loadDataFromDatabase(Pais pais) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            List<Marca> marcasCoches = CochesDatabaseManager.getInstance(this).getAllMarcasForCountriesBasicData(pais.getId());
            List<Marca> todas = new ArrayList<>();
            todas.addAll(marcasCoches);
            handler.post(() -> {
                binding.progressBar.setVisibility(View.INVISIBLE);
                binding.recyclerView.setVisibility(View.VISIBLE);
                marcasAdapter.refreshAdapter(todas);
            });
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
        float itemWidth = getResources().getDimension(R.dimen.list_item_modelo_width);
        int numColumns =  (int) (width / itemWidth);
        GridLayoutManager layoutManager = new GridLayoutManager(this, numColumns);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * que te lleve a la página de ModelosActivity.
     */

    private void navigateToModelosActivity(Marca marca) {
        Intent intent = new Intent(this, ModelosActivity.class);
        String json = com.example.collectorsgarage.collectorGarage.GenericUtils.serialize(marca);
        intent.putExtra("marca", json);
        startActivity(intent);
    }
}