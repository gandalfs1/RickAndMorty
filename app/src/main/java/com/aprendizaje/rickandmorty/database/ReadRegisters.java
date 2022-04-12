package com.aprendizaje.rickandmorty.database;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.*;

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
    public Api readChaCEpi(int idCha){
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
            Api api = new Api();
            String query = "SELECT * FROM " + CREATE_TABLE_CHA_X_EPI;
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null){
                cursor.moveToFirst();

            }
            return api;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
