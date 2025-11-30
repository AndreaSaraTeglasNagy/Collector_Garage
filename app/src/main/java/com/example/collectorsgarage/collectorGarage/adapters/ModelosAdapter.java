package com.example.collectorsgarage.collectorGarage.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.collectorsgarage.collectorGarage.objetos.Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ModelosAdapter extends RecyclerView.Adapter<ModelosAdapter.ViewHolder> {

    private Context mContext;
    private List<Modelo> modelos;
    private OnModeloClickedListener listener;


    /**
     * ModelosAdapter: Muestra una lista de modelos y avisa cuando alguien toca una modelo para
     * que se pueda acceder a la siguiente página.
     */


    public ModelosAdapter(Context context, OnModeloClickedListener listener) {
        mContext = context;
        this.listener = listener;
        this.modelos = new ArrayList<>();

    }

    public interface OnModeloClickedListener {
        void onClick(Modelo modelos);
    }

    /**
     *refeshAdapter: Te muestra desde la posición 0 de la lista de Modelos hasta la última posición que
     * existe en esa lista, si pones que termine en la posición 4 solo te mostrará los primeros 4 modelos.
     */

    public void refreshAdapter(List<Modelo> elements) {
        notifyItemRangeRemoved(0, modelos.size());
        this.modelos = elements;
        notifyItemRangeChanged(0, modelos.size());

    }


    /**
     * onBindViewHolder: A este método le explicas como quieres que te pinte los datos por pantalla.
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Modelo modelo = modelos.get(position);
        String imagenUrl = "https://www.timandorra.com/wp-content/uploads/2016/11/Imagen-no-disponible.png";
        if (modelo.getImagen() != null) {
            imagenUrl = modelo.getImagen();
        }

        holder.binding.textmodelo.setText(modelo.getNombre());
        Glide.with(mContext).load(Uri.parse(imagenUrl)).into(holder.binding.imagenmodelo);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(com.example.collectorsgarage.databinding.ListItemModeloBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }


    /**
     * Te devuelve el numero de datos que tiene modelos.
     */


    @Override
    public int getItemCount() {
        int size = 0;
        if (Objects.nonNull(modelos) && !modelos.isEmpty()) {
            size = modelos.size();
        }
        return size;
    }

    /**
     * Guarda la información con el método de OnClickLisener de que botón toca el usuario dentro del RecyclerView
     */


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        com.example.collectorsgarage.databinding.ListItemModeloBinding binding;

        ViewHolder(com.example.collectorsgarage.databinding.ListItemModeloBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
             listener.onClick(modelos.get(getAdapterPosition()));
        }
    }

}
