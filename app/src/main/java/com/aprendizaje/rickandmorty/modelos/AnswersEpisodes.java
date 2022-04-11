package com.aprendizaje.rickandmorty.modelos;
import static com.aprendizaje.rickandmorty.MainActivity.*;
import java.util.ArrayList;

public class AnswersEpisodes {

    Info info;
    ArrayList<Episode> results;

    public static AnswersEpisodes getInstance() {
        if (answersEpisodes == null){
            answersEpisodes = new AnswersEpisodes();
        }
        return answersEpisodes;
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
