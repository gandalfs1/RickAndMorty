package com.aprendizaje.rickandmorty.modelos;

import java.util.ArrayList;

public class AnswersCharacters {
    Info info;
    ArrayList<Results> results;

    public AnswersCharacters() {
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
