package com.example.collectorsgarage.collectorGarage.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.collectorsgarage.collectorGarage.objetos.Marca;
import com.example.collectorsgarage.collectorGarage.objetos.Modelo;
import com.example.collectorsgarage.collectorGarage.objetos.Pais;
import com.example.collectorsgarage.collectorGarage.objetos.TipoVehiculo;


import java.util.ArrayList;
import java.util.List;

public class CochesDatabaseManager {

    public static final String NOMBE_COLUMN_NAME = "Nombre";

    private static CochesDatabaseManager instance;
    private SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    private int mOpenCounter;


    private CochesDatabaseManager(Context context) {
        this.mDatabaseHelper = new CochesDatabaseHelper(context);
    }

    public synchronized static CochesDatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new CochesDatabaseManager(context.getApplicationContext());
        }
        return instance;
    }


    private synchronized void open() {
        mOpenCounter++;
        if (mOpenCounter == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
    }


    private synchronized void close() {
        mOpenCounter--;
        if (mOpenCounter == 0) {
            // Closing database
            mDatabase.close();

        }
    }

    @SuppressLint("Range")
    public List<Marca> getAllMarcasBasicData() {
        open();
        String query = "SELECT ID, Nombre, Pais, Logo" + " \n" +
                "FROM marcas \n";
        final Cursor cursor = mDatabase
                .rawQuery(query, null);

        final ArrayList<Marca> marcasList = new ArrayList<>();

        while (cursor.moveToNext()) {
            marcasList.add(new Marca(
                    cursor.getString(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("Nombre")),
                    null,
                    cursor.getString(cursor.getColumnIndex("Logo")),
                    TipoVehiculo.COCHE)
            );

        }
        cursor.close();
        close();
        return marcasList;
    }



    @SuppressLint("Range")
    public List<Marca> getAllMarcasForCountriesBasicData(String idPais) {
        open();
        String query = "SELECT ID, Nombre, Pais, Logo" + " \n" +
                "FROM marcas \n" +
                "WHERE Pais = '" + idPais + "'";
        final Cursor cursor = mDatabase
                .rawQuery(query, null);

        final ArrayList<Marca> marcasList = new ArrayList<>();

        while (cursor.moveToNext()) {
            marcasList.add(new Marca(
                    cursor.getString(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("Nombre")),
                    null,
                    cursor.getString(cursor.getColumnIndex("Logo")),
                    TipoVehiculo.COCHE)
            );

        }
        cursor.close();
        close();
        return marcasList;
    }



    @SuppressLint("Range")
    public List<Pais> getAllCountriesBasicData() {
        open();
        String query = "SELECT ID, Nombre, Bandera" + " \n" +
                "FROM paises \n";
        final Cursor cursor = mDatabase
                .rawQuery(query, null);

        final ArrayList<Pais> paisList = new ArrayList<>();

        while (cursor.moveToNext()) {
            paisList.add(new Pais(
                    cursor.getString(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("Nombre")),
                    cursor.getString(cursor.getColumnIndex("Bandera")))
            );

        }
        cursor.close();
        close();
        return paisList;
    }



    @SuppressLint("Range")
    public List<Modelo> getAllModelosForMarca(String idMarca) {
        open();
        String query = "SELECT mo.ID as moID, ma.ID as maID, mo.Descripcion as moDescripcion, mo.Precio as moPrecio, mo.Nombre as moNombre, pa.Bandera as paBandera, ma.Nombre as maNombre, mo.Imagen as moImagen, ma.Pais as maPais, ma.Logo as maLogo, pa.ID as paID, pa.Nombre as paNombre \n" +
                "FROM modelos as mo, marcas as ma, paises as pa " +
                "WHERE mo.marca = ma.ID " +
                "and  pa.ID = ma.Pais " +
                "and marca = '" + idMarca + "'";
        final Cursor cursor = mDatabase
                .rawQuery(query, null);

        final ArrayList<Modelo> modeloList = new ArrayList<>();

        while (cursor.moveToNext()) {

            Pais pais = new Pais(
                    getStringFromCursor(cursor, "paID"),
                    getStringFromCursor(cursor, "paNombre"),
                    getStringFromCursor(cursor, "paBandera")
            );

            Marca marca = new Marca(
                    getStringFromCursor(cursor, "maID"),
                    getStringFromCursor(cursor, "maNombre"),
                    pais,
                    getStringFromCursor(cursor, "maLogo"),
                    TipoVehiculo.COCHE
            );


            modeloList.add(new Modelo(
                    getStringFromCursor(cursor, "moID"),
                    getStringFromCursor(cursor, "moNombre"),
                    marca,
                    getStringFromCursor(cursor, "moImagen"),
                    getStringFromCursor(cursor, "moDescripcion"),
                    getStringFromCursor(cursor, "moPrecio")
            ));


        }
        cursor.close();
        close();
        return modeloList;
    }


    @SuppressLint("Range")
    private String getStringFromCursor(Cursor cursor, String nombreColumna) {
        return cursor.getString(cursor.getColumnIndex(nombreColumna));
    }

}