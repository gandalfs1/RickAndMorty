package com.aprendizaje.rickandmorty.modelos;

import static com.aprendizaje.rickandmorty.MainActivity.answersLocations;

import java.util.ArrayList;

public class AnswersLocations {
    Info info;
    ArrayList<Location> results;

    public static AnswersLocations getInstance() {
        if (answersLocations == null){
            answersLocations = new AnswersLocations();
        }
        return answersLocations;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Location> getResults() {
        return results;
    }

    public void setResults(ArrayList<Location> results) {
        this.results = results;
    }
}
