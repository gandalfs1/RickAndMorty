package com.aprendizaje.rickandmorty.modelos;

import java.util.ArrayList;

public class AnswersLocations {
    Info info;
    ArrayList<Location> results;

    public AnswersLocations() {
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
