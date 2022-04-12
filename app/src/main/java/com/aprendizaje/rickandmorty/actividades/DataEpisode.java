package com.aprendizaje.rickandmorty.actividades;

import static com.aprendizaje.rickandmorty.MainActivity.characterModel;
import static com.aprendizaje.rickandmorty.MainActivity.episodeModel;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.modelos.Character;
import com.aprendizaje.rickandmorty.modelos.Episode;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class DataEpisode extends AppCompatActivity {
    String url ;
    Gson gson;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_episode);
        gson = new Gson();
        requestQueue = Volley.newRequestQueue(this);
        Bundle extras = getIntent().getExtras();
        url = extras.getString(URL);
        episodeModel = Episode.getInstance();
        jsonObjet();
    }

    private void jsonObjet() {
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                episodeModel = gson.fromJson(response.toString(), Episode.class);

                arrar(episodeModel);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonRequest);
    }

    private void arrar(Episode episode) {
    }
}