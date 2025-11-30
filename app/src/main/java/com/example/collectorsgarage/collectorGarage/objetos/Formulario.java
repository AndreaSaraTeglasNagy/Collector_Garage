package com.example.collectorsgarage.collectorGarage.objetos;

import java.io.Serializable;

public class Formulario implements Serializable {

    private String nombre;
    private String descripcion;
    private String motivo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
