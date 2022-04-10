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

    public static final String URL = "url";

    public static final String TABLE_lOCATIONS = "locations";
    public static final String COLUMN_ID_lOCATION = "col_id_location";
    public static final String COLUMN_NAME_lOCATION = "col_name_location";
    public static final String COLUMN_TYPE_lOCATION = "col_type_location";
    public static final String COLUMN_DIMENSION_lOCATION = "col_dimension_location";
    public static final String COLUMN_RESIDENTS_lOCATION = "col_residents_location";
    public static final String COLUMN_URL_lOCATION = "col_url_location";
    public static final String COLUMN_CREATED_lOCATION = "col_created_location";
    public static final String CREATE_TABLE_LOCATIONS = "CREATE TABLE " + TABLE_lOCATIONS + " ( "
            + COLUMN_ID_lOCATION + " INTEGER,"
            + COLUMN_NAME_lOCATION + " TEXT,"
            + COLUMN_TYPE_lOCATION + " TEXT,"
            + COLUMN_DIMENSION_lOCATION + " TEXT,"
            + COLUMN_RESIDENTS_lOCATION + " TEXT,"
            + COLUMN_URL_lOCATION + " TEXT,"
            + COLUMN_CREATED_lOCATION + " TEXT );";

    public static final String TABLE_EPISODES ="episodes";
    public static final String COLUMN_ID_EPISODE = "col_id_episode";
    public static final String COLUMN_NAME_EPISODE = "col_name_episode";
    public static final String COLUMN_AIR_DATE_EPISODE = "col_air_date_episode";
    public static final String COLUMN_CODE_EPISODE = "col_code_episode";
    public static final String COLUMN_CHARACTES_EPISODE = "col_character_episode";
    public static final String COLUMN_URL_EPISODE = "col_url_episode";
    public static final String COLUMN_CREATED_EPISODE = "col_created_episode";
    public static final String CREATE_TABLE_EPISODES = "CREATE TABLE " + TABLE_EPISODES + " ( "
            + COLUMN_ID_lOCATION + " INTEGER,"
            + COLUMN_NAME_EPISODE + " TEXT,"
            + COLUMN_AIR_DATE_EPISODE + " TEXT,"
            + COLUMN_CODE_EPISODE + " TEXT,"
            + COLUMN_CHARACTES_EPISODE + " TEXT,"
            + COLUMN_URL_EPISODE + " TEXT,"
            + COLUMN_CREATED_EPISODE + " TEXT,"
            + COLUMN_CREATED_lOCATION + " TEXT );";

    public static final String TABLE_CHARACTERS ="characters";
    public static final String COLUMN_ID_CHARACTER = "col_id_character";
    public static final String COLUMN_NAME_CHARACTER = "col_name_character";
    public static final String COLUMN_STATUS_CHARACTER = "col_status_character";
    public static final String COLUMN_SPECIES_CHARACTER = "col_species_character";
    public static final String COLUMN_TYPE_CHARACTER = "col_type_character";
    public static final String COLUMN_GENDER_CHARACTER = "col_gender_character";
    public static final String COLUMN_ORIGIN_CHARACTER = "col_origin_character";
    public static final String COLUMN_LOCATION_CHARACTER = "col_location_character";
    public static final String COLUMN_IMAGE_CHARACTER = "col_image_character";
    public static final String COLUMN_EPISODE_CHARACTER = "col_episode_character";
    public static final String COLUMN_URL_CHARACTER = "col_url_character";
    public static final String COLUMN_CREATED_CHARACTER = "col_created_character";
    public static final String CREATE_TABLE_CHARACTERS = "CREATE TABLE " + TABLE_CHARACTERS + " ( "
            + COLUMN_ID_CHARACTER + " INTEGER,"
            + COLUMN_NAME_CHARACTER + " TEXT,"
            + COLUMN_STATUS_CHARACTER + " TEXT,"
            + COLUMN_SPECIES_CHARACTER + " TEXT,"
            + COLUMN_TYPE_CHARACTER + " TEXT,"
            + COLUMN_GENDER_CHARACTER + " TEXT,"
            + COLUMN_ORIGIN_CHARACTER + " TEXT,"
            + COLUMN_LOCATION_CHARACTER + " TEXT,"
            + COLUMN_IMAGE_CHARACTER + " TEXT,"
            + COLUMN_EPISODE_CHARACTER + " TEXT,"
            + COLUMN_URL_CHARACTER + " TEXT,"
            + COLUMN_CREATED_CHARACTER + " TEXT );";


    public static final String IMG_MENU_CHARACTERS = "https://media.vogue.es/photos/5cc754b2c702372f61fe0388/master/w_1600,c_limit/rick_y_morty_7132.jpg";
    public static final String IMG_MENU_LOCATIONS = "https://images8.alphacoders.com/642/thumb-1920-642527.jpg";
    public static final String IMG_MENU_EPISODES = "https://static1.colliderimages.com/wordpress/wp-content/uploads/2021/05/rick-and-morty-best-episodes.jpg";
    public static final String IMG_MENU_FAVORITES = "https://static1.colliderimages.com/wordpress/wp-content/uploads/2019/11/rick-and-morty-the-old-man-and-the-seat.jpg?q=50&fit=crop&w=1920&dpr=1.5";
    public static final String NAME_MENU_CHARACTES = "CHARACTERS";
    public static final String NAME_MENU_LOCATIONS = "LOCATIONS";
    public static final String NAME_MENU_EPISODES = "EPISODES";
    public static final String NAME_MENU_FAVORITES ="FAVORITES";
}
