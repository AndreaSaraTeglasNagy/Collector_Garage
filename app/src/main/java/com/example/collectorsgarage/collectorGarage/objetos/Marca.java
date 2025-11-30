package com.example.collectorsgarage.collectorGarage.objetos;

import android.net.Uri;

public class Marca {


    private String id;
    private String nombre;
    private Pais pais;
    private String imagen;
    private TipoVehiculo tipoVehiculo;

    public Marca(String id, String nombre, Pais pais, String imagen, TipoVehiculo tipoVehiculo) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.imagen = imagen;
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
