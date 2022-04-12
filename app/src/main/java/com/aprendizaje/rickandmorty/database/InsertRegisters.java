package com.aprendizaje.rickandmorty.database;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aprendizaje.rickandmorty.modelos.Api;
import com.aprendizaje.rickandmorty.modelos.Character;
import com.aprendizaje.rickandmorty.modelos.Episode;
import com.aprendizaje.rickandmorty.modelos.Location;

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

    public long insertCharacter(ArrayList<Character> characterArrayList){
        long id = 0;
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
            for (int i = 0; i < characterArrayList.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_ID_CHARACTER,characterArrayList.get(i).getId());
                contentValues.put(COLUMN_NAME_CHARACTER,characterArrayList.get(i).getName());
                contentValues.put(COLUMN_STATUS_CHARACTER,characterArrayList.get(i).getCreated());
                contentValues.put(COLUMN_TYPE_CHARACTER,characterArrayList.get(i).getCreated());
                contentValues.put(COLUMN_GENDER_CHARACTER,characterArrayList.get(i).getCreated());
                contentValues.put(COLUMN_ORIGIN_CHARACTER,characterArrayList.get(i).getCreated());
                contentValues.put(COLUMN_LOCATION_CHARACTER,characterArrayList.get(i).getCreated());
                contentValues.put(COLUMN_IMAGE_CHARACTER,characterArrayList.get(i).getCreated());
                contentValues.put(COLUMN_EPISODE_CHARACTER,characterArrayList.get(i).getCreated());
                insertChaXEpi(characterArrayList.get(i).getEpisode(),characterArrayList.get(i).getId());
                contentValues.put(COLUMN_URL_CHARACTER,characterArrayList.get(i).getCreated());
                contentValues.put(COLUMN_CREATED_CHARACTER,characterArrayList.get(i).getCreated());
                id = sqLiteDatabase.insert(TABLE_CHARACTERS, null,contentValues);
            }
            return  id;

        } catch (Exception e) {
            e.printStackTrace();
            return id;
        }
    }

    public long insertLocation(ArrayList<Location> locationArrayList){
        long id = 0;
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
            for (int i = 0; i < locationArrayList.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_ID_lOCATION,locationArrayList.get(i).getId());
                contentValues.put(COLUMN_NAME_lOCATION,locationArrayList.get(i).getName());
                contentValues.put(COLUMN_TYPE_lOCATION,locationArrayList.get(i).getType());
                contentValues.put(COLUMN_DIMENSION_lOCATION,locationArrayList.get(i).getDimension());
                insertLocXCha(locationArrayList.get(i).getResidents(),locationArrayList.get(i).getId());
                contentValues.put(COLUMN_URL_lOCATION,locationArrayList.get(i).getUrl());
                contentValues.put(COLUMN_CREATED_lOCATION,locationArrayList.get(i).getCreated());
                id = sqLiteDatabase.insert(TABLE_lOCATIONS, null,contentValues);
            }
            return  id;

        } catch (Exception e) {
            e.printStackTrace();
            return id;
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
                contentValues.put(COLUMN_ID_CHA,idCha);
                contentValues.put(COLUMN_ID_EPI,episodeArrayList.get(i).substring(40));
                sqLiteDatabase.insert(TABLE_CHA_X_EPI, null,contentValues);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertLocXCha(ArrayList<String> characterArrayList, int idLoc){

        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
            for (int i = 0; i < characterArrayList.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_ID_LOC,idLoc);
                contentValues.put(COLUMN_ID_CHA,characterArrayList.get(i).substring(42));
                sqLiteDatabase.insert(TABLE_LOC_X_CHA, null,contentValues);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
