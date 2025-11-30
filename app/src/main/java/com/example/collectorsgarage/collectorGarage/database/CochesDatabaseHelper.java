package com.example.collectorsgarage.collectorGarage.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class CochesDatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "CollectorGarage.db";
    private static final int DATABASE_VERSION = 1;

    CochesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }

}
