package com.aprendizaje.rickandmorty.database;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aprendizaje.rickandmorty.modelos.AnswersEpisodes;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.aprendizaje.rickandmorty.modelos.Character;
import com.aprendizaje.rickandmorty.modelos.Episode;

import java.util.ArrayList;


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

    public long insertEpisodes(ArrayList<Episode> episodeArrayList){
        long id = 0;
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
            for (int i = 0; i < episodeArrayList.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_ID_EPISODE,episodeArrayList.get(i).getId());
                contentValues.put(COLUMN_NAME_EPISODE,episodeArrayList.get(i).getName());
                contentValues.put(COLUMN_AIR_DATE_EPISODE,episodeArrayList.get(i).getAir_date());
                contentValues.put(COLUMN_CODE_EPISODE,episodeArrayList.get(i).getCodeEpisode());
                insertChaXEpi(episodeArrayList.get(i).getCharacters(),episodeArrayList.get(i).getId());
                contentValues.put(COLUMN_URL_EPISODE,episodeArrayList.get(i).getUrl());
                contentValues.put(COLUMN_CREATED_EPISODE,episodeArrayList.get(i).getCreated());
                id = sqLiteDatabase.insert(TABLE_EPISODES, null,contentValues);
            }
            return  id;

        } catch (Exception e) {
            e.printStackTrace();
            return id;
        }
    }
    public void insertChaXEpi(ArrayList<String> episodeArrayList, int idCha){

        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
            for (int i = 0; i < episodeArrayList.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_ID_CHA,episodeArrayList.get(i).substring(42));
                contentValues.put(COLUMN_ID_EPI,idCha);
                sqLiteDatabase.insert(TABLE_CHA_X_EPI, null,contentValues);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
