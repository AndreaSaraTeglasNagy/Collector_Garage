package com.example.collectorsgarage.collectorGarage.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.collectorsgarage.collectorGarage.objetos.Marca;
import com.example.collectorsgarage.databinding.ListItemMarcaBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MarcaAdapter extends RecyclerView.Adapter<MarcaAdapter.ViewHolder> {

    private Context mContext;
    private List<Marca> marcas;
    private MarcaAdapter.OnMarcaClickedListener listener;

    /**
     * MarcasAdapter: Muestra una lista de marcas y avisa cuando alguien toca una marca para
     * que se pueda acceder a la siguiente página.
     */


    public MarcaAdapter(Context context, MarcaAdapter.OnMarcaClickedListener listener) {
        mContext = context;
        this.listener = listener;
        this.marcas = new ArrayList<>();
    }

    public interface OnMarcaClickedListener {
        void onClick(Marca marca);
    }


    /**
     *refeshAdapter: Te muestra desde la posición 0 de la lista de Marcas hasta la última posición que existe en Marcas,
     * si pones que termine en la posición 4 solo te mostrará las primeras cuatro marcas.
     */

    public void refreshAdapter(List<Marca> elements) {
        notifyItemRangeRemoved(0, marcas.size());
        this.marcas = elements;
        notifyItemRangeChanged(0, marcas.size());
    }


    /**
     * onBindViewHolder: A este método le explicas como quieres que te pinte los datos por pantalla.
     */

    @NonNull
    @Override
    public MarcaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MarcaAdapter.ViewHolder(ListItemMarcaBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Marca marca = marcas.get(position);
        String imagenUrl = "https://www.timandorra.com/wp-content/uploads/2016/11/Imagen-no-disponible.png";
        if (marca.getImagen() != null) {
            imagenUrl = marca.getImagen();
        }
        holder.binding.textlogo.setText(marca.getNombre());

        Glide.with(mContext).load(Uri.parse(imagenUrl)).into(holder.binding.logo);
    }

    /**
     * Te devuelve el numero de datos que tiene marcas.
     */

    @Override
    public int getItemCount() {
        int size = 0;
        if (Objects.nonNull(marcas) && !marcas.isEmpty()) {
            size = marcas.size();
        }
        return size;
    }

    /**
     * Guarda la información con el método de OnClickLisener de que botón toca el usuario dentro del RecyclerView
     */

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ListItemMarcaBinding binding;

        ViewHolder(ListItemMarcaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(marcas.get(getAdapterPosition()));
        }
    }

}
