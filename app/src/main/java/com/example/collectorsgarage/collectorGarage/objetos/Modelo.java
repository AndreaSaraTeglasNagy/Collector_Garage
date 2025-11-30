package com.example.collectorsgarage.collectorGarage.objetos;

import android.net.Uri;

public class Modelo {

    private String id;
    private String nombre;
    private Marca marca;
    private String imagen;
    private String descripcion;
    private String precio;

    public Modelo(String id, String nombre, Marca marca, String imagen, String descripcion, String precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }


}
