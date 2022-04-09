package com.aprendizaje.rickandmorty.utilidades;

public class Constantes {
    public static final String BASEURL = "https://rickandmortyapi.com/api";
    public static final String DATABASENAME = "rickandmortyapi.db";

    public static final String TABLE_URLS = "urls";
    public static final String COLUMN_URL_CHARACTERS = "col_characters";
    public static final String COLUMN_URL_LOCATIONS = "col_locations";
    public static final String COLUMN_URL_EPISODES = "col_espisodes";
    public static final String CREATE_TABLE_URLS = "CREATE TABLE " + TABLE_URLS + " ( "
            + COLUMN_URL_CHARACTERS + " TEXT,"
            + COLUMN_URL_LOCATIONS + " TEXT,"
            + COLUMN_URL_EPISODES + " TEXT );";
}
