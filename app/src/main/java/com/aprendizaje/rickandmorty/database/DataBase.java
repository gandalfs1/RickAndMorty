package com.aprendizaje.rickandmorty.database;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {


    public DataBase(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_URLS);
        sqLiteDatabase.execSQL(CREATE_TABLE_CHARACTERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_LOCATIONS);
        sqLiteDatabase.execSQL(CREATE_TABLE_EPISODES);
        sqLiteDatabase.execSQL(CREATE_TABLE_CHA_X_EPI);
        sqLiteDatabase.execSQL(CREATE_TABLE_LOC_X_CHA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
