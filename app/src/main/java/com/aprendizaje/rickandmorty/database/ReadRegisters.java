package com.aprendizaje.rickandmorty.database;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aprendizaje.rickandmorty.modelos.Api;

public class ReadRegisters extends DataBase{
    Context context;
    public ReadRegisters(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    @SuppressLint("Range")
    public Api readUrls(){
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
            Api api = new Api();
            String query = "SELECT * FROM "+ TABLE_URLS;
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null){
                cursor.moveToFirst();
                api.setCharacters(cursor.getString(0));
                api.setLocations(cursor.getString(1));
                api.setEpisodes(cursor.getString(2));
            }
            return api;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
