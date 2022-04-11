package com.aprendizaje.rickandmorty.modelos;

import com.aprendizaje.rickandmorty.MainActivity;

public class Api {
    String characters;
    String locations;
    String episodes;

    public static Api getInstance() {
        if (MainActivity.api == null){
            MainActivity.api = new Api();
        }
        return MainActivity.api;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }
}
