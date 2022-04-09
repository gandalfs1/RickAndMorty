package com.aprendizaje.rickandmorty.modelos;

import java.util.ArrayList;

public class AnswersEpisodes {

    Info info;
    ArrayList<Episode> results;

    public AnswersEpisodes() {
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Episode> getResults() {
        return results;
    }

    public void setResults(ArrayList<Episode> results) {
        this.results = results;
    }
}
