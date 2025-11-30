package com.example.collectorsgarage.collectorGarage.objetos;

public class Pais {

    private String id;
    private String nombre;
    private String bandera;


    public Pais(String id, String nombre, String bandera) {
        this.id = id;
        this.nombre = nombre;
        this.bandera = bandera;

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

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }
}
