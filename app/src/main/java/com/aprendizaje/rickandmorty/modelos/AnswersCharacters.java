package com.aprendizaje.rickandmorty.modelos;
import static com.aprendizaje.rickandmorty.MainActivity.answersCharacters;

import java.util.ArrayList;

public class AnswersCharacters {
    Info info;
    ArrayList<Character> results;

    public static AnswersCharacters getInstance() {
        if (answersCharacters == null){
            answersCharacters = new AnswersCharacters();
        }
        return answersCharacters;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Character> getResults() {
        return results;
    }

    public void setResults(ArrayList<Character> results) {
        this.results = results;
    }
}
