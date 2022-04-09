package com.aprendizaje.rickandmorty.actividades;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.BASEURL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
import com.aprendizaje.rickandmorty.adaptadores.AdapterCharacters;
import com.aprendizaje.rickandmorty.adaptadores.AdapterEpisode;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.aprendizaje.rickandmorty.modelos.Results;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class DataCharacter extends AppCompatActivity {

    AdapterEpisode adapterEpisode;
    RecyclerView recyclerViewEpisodes;
    int id = 0;
    Results results;
    Gson gson;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_character);
        recyclerViewEpisodes = findViewById(R.id.recyclerViewEpisodes);
        results = new Results();
        gson = new Gson();

        requestQueue = Volley.newRequestQueue(this);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("ID");
        jsonObjet();
    }

    private void jsonObjet() {
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, BASEURL + "/character/" + id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                results = gson.fromJson(response.toString(), Results.class);

                arrar(results.getEpisode());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonRequest);
    }

    private void arrar(ArrayList<String> results) {
        adapterEpisode = new AdapterEpisode(results, DataCharacter.this);
        recyclerViewEpisodes.setLayoutManager(new GridLayoutManager(this,4));
        recyclerViewEpisodes.setAdapter(adapterEpisode);
    }
}