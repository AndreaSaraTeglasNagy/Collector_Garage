package com.example.collectorsgarage.collectorGarage.coches;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collectorsgarage.R;
import com.example.collectorsgarage.collectorGarage.GenericUtils;
import com.example.collectorsgarage.collectorGarage.adapters.ModelosAdapter;
import com.example.collectorsgarage.collectorGarage.database.CochesDatabaseManager;
import com.example.collectorsgarage.collectorGarage.objetos.Marca;
import com.example.collectorsgarage.collectorGarage.objetos.Modelo;
import com.example.collectorsgarage.databinding.ActivityModelosBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ModelosActivity extends AppCompatActivity {

    private ActivityModelosBinding binding;
    private ModelosAdapter modelosAdapter;
    private List<Modelo> modelos;

    /**
     * recoge la información de la pagina MarcasActivity con el Intent y el getStringExtra y te la muestra por pantalla.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModelosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String json = intent.getStringExtra("marca");
        Marca marca = GenericUtils.deserialize(json, Marca.class);
        int width = binding.widthModelo.getWidth();
        getSupportActionBar().setTitle(marca.getNombre());
        loadDataFromDatabase(marca);
        initializeRecyclerView();
        initializeFilter();
    }

    private void loadDataFromDatabase(Marca marca) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            modelos = CochesDatabaseManager.getInstance(this).getAllModelosForMarca(marca.getId());
            handler.post(() -> {
                binding.progressBar.setVisibility(View.INVISIBLE);
                binding.recyclerViewModelos.setVisibility(View.VISIBLE);
                modelosAdapter.refreshAdapter(modelos);
            });
        });
    }
    private void initializeFilter() {

        binding.nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filtrar(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable charSequence) {

            }
        });


    }

    /**
     * Indicas cómo se ve y se comporta la lista de modelos en la aplicación. Configura el adaptador para
     * manejar los modelos, defines cómo se va a organizar la lista y luego le dices a la lista que se actualice
     * con los modelos nuevos
     */

    private void initializeRecyclerView() {
        modelosAdapter = new ModelosAdapter(this, new ModelosAdapter.OnModeloClickedListener() {
            @Override
            public void onClick(Modelo modelos) {

                navigateToCaracteristicasModelo(modelos);
            }

        });
        instantiateAndSetupLayoutManager(binding.recyclerViewModelos);
        binding.recyclerViewModelos.setAdapter(modelosAdapter);
    }

    private void instantiateAndSetupLayoutManager(RecyclerView recyclerViewModelos) {
        float width = Resources.getSystem().getDisplayMetrics().widthPixels;
        float itemWidth = getResources().getDimension(R.dimen.list_item_modelo_width);
        int numColumns =  (int) (width / itemWidth);
        GridLayoutManager layoutManager = new GridLayoutManager(this, numColumns);
        recyclerViewModelos.setLayoutManager(layoutManager);
    }

    private void navigateToCaracteristicasModelo(Modelo modelo) {
        Intent intent = new Intent(this, CaracteristicasModeloActivity.class);
        String json = GenericUtils.serialize(modelo);
        intent.putExtra("modelo", json);
        startActivity(intent);
    }

    private void filtrar(String inputnombre) {
        List<Modelo> modelosFiltrados = new ArrayList<>();
        for (Modelo modelo : modelos) {
            String nombreModelo = modelo.getNombre().toLowerCase();
            if (nombreModelo.contains(inputnombre.toLowerCase())) {
                modelosFiltrados.add(modelo);
            }
        }
        modelosAdapter.refreshAdapter(modelosFiltrados);
    }

}