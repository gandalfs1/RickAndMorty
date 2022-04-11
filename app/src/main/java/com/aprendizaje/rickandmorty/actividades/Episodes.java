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
import static com.aprendizaje.rickandmorty.MainActivity.*;
import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.adaptadores.AdapterEpisodes;
import com.aprendizaje.rickandmorty.database.InsertRegisters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.AnswersEpisodes;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Episodes extends AppCompatActivity {

    InsertRegisters insertRegisters;
    Gson gson;
    RequestQueue requestQueue;
    ReadRegisters readRegisters;
    RecyclerView recyclerViewEpisode;
    AdapterEpisodes adapterEpisodes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);
        init();
    }

    private void init(){
        insertRegisters = new InsertRegisters(this);
        answersEpisodes = AnswersEpisodes.getInstance();
        gson = new Gson();
        requestQueue = Volley.newRequestQueue(this);
        readRegisters = new ReadRegisters(this);
        api = Api.getInstance();
        readRegisters = new ReadRegisters(this);
        recyclerViewEpisode = findViewById(R.id.recyclerViewEpisode);
        getEpisodes();
    }

    private void getEpisodes(){
    JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, api.getEpisodes(), null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            answersEpisodes = gson.fromJson(response.toString(), AnswersEpisodes.class);
            Toast.makeText(Episodes.this, answersEpisodes.getResults().get(2).getName(), Toast.LENGTH_SHORT).show();
            adapterEpisodes = new AdapterEpisodes(answersEpisodes.getResults(),Episodes.this);
            recyclerViewEpisode.setLayoutManager(new GridLayoutManager(Episodes.this,1));
            recyclerViewEpisode.setAdapter(adapterEpisodes);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(Episodes.this, "mal ", Toast.LENGTH_SHORT).show();
        }
    });

    requestQueue.add(jsonRequest);
    }
}