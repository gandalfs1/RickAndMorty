package com.aprendizaje.rickandmorty.database;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.*;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aprendizaje.rickandmorty.modelos.Api;
import com.aprendizaje.rickandmorty.modelos.Character;
import com.aprendizaje.rickandmorty.modelos.Episode;
import com.aprendizaje.rickandmorty.modelos.Location;

import java.util.ArrayList;

public class ReadRegisters extends DataBase {
    Context context;

    public ReadRegisters(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public Api readUrls() {
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
            Api api = new Api();
            String query = "SELECT * FROM " + TABLE_URLS;
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null) {
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


    @SuppressLint("Range")
    public ArrayList<Character> readCharacter() {
        ArrayList<Character> listCharacter = new ArrayList<>();
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_CHARACTERS;
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    Character character = new Character();
                    character.setId((cursor.getColumnIndex(COLUMN_ID_CHARACTER)));
                    character.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER)));
                    character.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS_CHARACTER)));
                    character.setSpecies(cursor.getString(cursor.getColumnIndex(COLUMN_SPECIES_CHARACTER)));
                    character.setOrigin(readOrigin(cursor.getString(cursor.getColumnIndex(COLUMN_ORIGIN_CHARACTER))));
                    character.setLocation(readLocationName(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION_CHARACTER))));
                    character.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_CHARACTER)));
                    character.setEpisode(readChaXEpi(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID_CHARACTER))), "character"));
                    character.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL_CHARACTER)));
                    character.setCreated(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_CHARACTER)));
                    Episode episode = new Episode();
                    episode = readEpisode(character.getEpisode().get(0));
                    character.setFirstEpisode(episode.getName());
                    listCharacter.add(character);
                } while (cursor.moveToNext());
                //
                cursor.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCharacter;
    }

    @SuppressLint("Range")
    private Episode readEpisode(String idEpisode) {
        Episode episode = new Episode();
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

                String query = "SELECT * FROM " + TABLE_EPISODES + " WHERE "+ COLUMN_ID_EPISODE + " = "+idEpisode;
                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {

                        episode.setId((cursor.getColumnIndex(COLUMN_ID_EPISODE)));
                        episode.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_EPISODE)));
                        episode.setAir_date(cursor.getString(cursor.getColumnIndex(COLUMN_AIR_DATE_EPISODE)));
                        episode.setCodeEpisode(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER)));
                        episode.setCharacters(readChaXEpi(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID_EPISODE))), "episode"));
                        episode.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL_EPISODE)));
                        episode.setCreated(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_EPISODE)));
                        //listEpisode.add(episode);
                    } while (cursor.moveToNext());
                    cursor.close();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return episode;
    }

    public ArrayList<String> readChaXEpi(int id, String type) {
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
            ArrayList<String> list = new ArrayList<>();
            String query = null;
            switch (type) {
                case "episode":
                    query = "SELECT " + COLUMN_ID_CHA + " FROM " + TABLE_CHA_X_EPI + " WHERE " + COLUMN_ID_EPI + " ='" + id + "'";
                    break;
                case "character":
                    query = "SELECT " + COLUMN_ID_EPI + " FROM " + TABLE_CHA_X_EPI + " WHERE " + COLUMN_ID_CHA + " ='" + id + "'";
                    break;
            }

            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                list.add(cursor.getString(0));
            }
            cursor.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint("Range")
    public ArrayList<Episode> readEpisodes() {
        ArrayList<Episode> listEpisode = new ArrayList<>();
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_EPISODES;


            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    Episode episode = new Episode();
                    episode.setId((cursor.getColumnIndex(COLUMN_ID_EPISODE)));
                    episode.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_EPISODE)));
                    episode.setAir_date(cursor.getString(cursor.getColumnIndex(COLUMN_AIR_DATE_EPISODE)));
                    episode.setCodeEpisode(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER)));
                    episode.setCharacters(readChaXEpi(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID_EPISODE))), "episode"));
                    episode.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL_EPISODE)));
                    episode.setCreated(cursor.getString(cursor.getColumnIndex(COLUMN_CREATED_EPISODE)));
                    listEpisode.add(episode);
                } while (cursor.moveToNext());
                //
                cursor.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEpisode;
    }

    @SuppressLint("Range")
    public ArrayList<Character> readLocation() {
        ArrayList<Character> listCharacter = new ArrayList<>();
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_CHARACTERS;
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    Character character = new Character();
                    character.setId((cursor.getColumnIndex(COLUMN_ID_CHARACTER)));
                    character.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER)));
                    character.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER)));
                    character.setSpecies(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER)));
                    character.setOrigin(readOrigin(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER))));
                    character.setLocation(readLocationName(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION_CHARACTER))));
                    //  character.setEpisode(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER)));
                    character.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER)));
                    character.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHARACTER)));
                    listCharacter.add(character);
                } while (cursor.moveToNext());
                //
                cursor.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCharacter;
    }


    @SuppressLint("Range")
    public Origin readOrigin(String string) {
        Origin origin = new Origin();
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_lOCATIONS + " WHERE " + COLUMN_URL_lOCATION + " = '" + string + "'";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                origin.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_lOCATION)));
                origin.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL_lOCATION)));
                cursor.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return origin;
    }

    @SuppressLint("Range")
    public Location readLocationName(String string) {
        Location location = new Location();
        try {
            DataBase dataBase = new DataBase(context);
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_lOCATIONS + " WHERE " + COLUMN_URL_lOCATION + " = '" + string + "'";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                location.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_lOCATION)));
                location.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL_lOCATION)));
                cursor.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }
}
