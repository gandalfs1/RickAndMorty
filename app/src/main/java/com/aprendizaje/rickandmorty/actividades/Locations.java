package com.aprendizaje.rickandmorty.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.adaptadores.AdapterLocations;
import com.aprendizaje.rickandmorty.database.InsertRegisters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.AnswersLocations;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Locations extends AppCompatActivity {
    InsertRegisters insertRegisters;
    AnswersLocations answersLocations;
    Gson gson;
    RequestQueue requestQueue;
    ReadRegisters readRegisters;
    Api api;
    RecyclerView recyclerViewLocations;
    AdapterLocations adapterLocations;
    TextView txt ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        init();
    }
    private void init(){
        insertRegisters = new InsertRegisters(this);
        answersLocations = new AnswersLocations();
        gson = new Gson();
        requestQueue = Volley.newRequestQueue(this);
        readRegisters = new ReadRegisters(this);
        api = new Api();
        readRegisters = new ReadRegisters(this);
        api= readRegisters.readUrls();
        recyclerViewLocations = findViewById(R.id.recyclerViewLocations);
        getLocations();
    }
    private void getLocations(){
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, api.getLocations(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                answersLocations = gson.fromJson(response.toString(), AnswersLocations.class);
              //  Toast.makeText(Locations.this, " tama "+answersLocations.getResults().get(2).getResidents().size(), Toast.LENGTH_SHORT).show();
                adapterLocations = new AdapterLocations(answersLocations.getResults(),Locations.this);
                recyclerViewLocations.setLayoutManager(new GridLayoutManager(Locations.this,1));
                recyclerViewLocations.setAdapter(adapterLocations);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Locations.this, "mal ", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonRequest);
    }
}