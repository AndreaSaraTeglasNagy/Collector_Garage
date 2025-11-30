package com.example.collectorsgarage.collectorGarage.coches;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.collectorsgarage.collectorGarage.GenericUtils;
import com.example.collectorsgarage.collectorGarage.objetos.Modelo;
import com.example.collectorsgarage.databinding.ActivityCaracteristicasModeloBinding;
import com.example.collectorsgarage.databinding.ActivityMarcasBinding;

public class CaracteristicasModeloActivity extends AppCompatActivity {

    private ActivityCaracteristicasModeloBinding binding;




    /**
     * recoge la información de la pagina MarcasActivity con el Intent y el getStringExtra y te la muestra por pantalla.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCaracteristicasModeloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String json = intent.getStringExtra("modelo");
        Modelo modelo = GenericUtils.deserialize(json, Modelo.class);

        Glide.with(this).load(Uri.parse(modelo.getImagen())).into(binding.modeloCaracteristicas);
        String imagenUrl = "https://www.timandorra.com/wp-content/uploads/2016/11/Imagen-no-disponible.png";
        if (modelo.getMarca().getImagen() != null) {
            imagenUrl = modelo.getMarca().getImagen();
        }
        Glide.with(this).load(Uri.parse(imagenUrl)).into(binding.logo);


        binding.nombreMarca.setText(modelo.getMarca().getNombre());
        binding.nombreModelo.setText(modelo.getNombre());
        binding.nombrePais.setText(modelo.getMarca().getPais().getNombre());
        binding.descripcion.setText(modelo.getDescripcion());
        String formattedPrice = formatPrice(modelo.getPrecio());
        binding.precio.setText(formattedPrice);
    }

    private String formatPrice(String price) {

        StringBuilder formattedPrice = new StringBuilder();
        int length = price.length();

        for (int i = 0; i < length; i++) {

            if (i > 0 && i % 3 == 0) {
                formattedPrice.insert(0, '.');
            }
            formattedPrice.insert(0, price.charAt(length - 1 - i));
        }

        return formattedPrice.toString();
    }
}






