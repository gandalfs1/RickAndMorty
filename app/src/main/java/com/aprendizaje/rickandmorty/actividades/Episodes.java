package com.aprendizaje.rickandmorty.actividades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.aprendizaje.rickandmorty.MainActivity;
import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.database.InsertRegisters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.AnswersCharacters;
import com.aprendizaje.rickandmorty.modelos.AnswersEpisodes;
import com.aprendizaje.rickandmorty.modelos.AnswersLocations;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Episode extends AppCompatActivity {

    InsertRegisters insertRegisters;
    AnswersEpisodes answersEpisodes;
    Gson gson;
    RequestQueue requestQueue;
    ReadRegisters readRegisters;
    TextView name;
    Api api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);
        init();
    }

    private void init(){
        insertRegisters = new InsertRegisters(this);
        answersEpisodes = new AnswersEpisodes();
        gson = new Gson();
        requestQueue = Volley.newRequestQueue(this);
        readRegisters = new ReadRegisters(this);
        name = findViewById(R.id.nameEpisode);
        api = new Api();
        readRegisters = new ReadRegisters(this);
        api= readRegisters.readUrls();
        getEpisodes();
    }

    private void getEpisodes(){
    JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, api.getEpisodes(), null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            answersEpisodes = gson.fromJson(response.toString(), AnswersEpisodes.class);
            Toast.makeText(Episode.this, answersEpisodes.getResults().get(2).getName(), Toast.LENGTH_SHORT).show();

               insertRegisters.insertEpisodes(answersEpisodes.getResults());


           // name.setText(response.toString());


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(Episode.this, "mal ", Toast.LENGTH_SHORT).show();
        }
    });

    requestQueue.add(jsonRequest);
    }
}