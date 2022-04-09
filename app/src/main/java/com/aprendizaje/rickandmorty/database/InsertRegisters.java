package com.aprendizaje.rickandmorty.database;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aprendizaje.rickandmorty.modelos.Api;


public class InsertRegisters extends DataBase{
    Context context;
    public InsertRegisters(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertUrls(Api api){
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_URL_CHARACTERS,api.getCharacters());
            contentValues.put(COLUMN_URL_LOCATIONS,api.getLocations());
            contentValues.put(COLUMN_URL_EPISODES,api.getEpisodes());
            return  sqLiteDatabase.insert(TABLE_URLS, null,contentValues);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
