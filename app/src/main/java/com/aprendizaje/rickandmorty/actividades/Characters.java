package com.aprendizaje.rickandmorty.actividades;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.BASEURL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.aprendizaje.rickandmorty.adaptadores.AdapterCharacters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.Answers;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.aprendizaje.rickandmorty.modelos.Results;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class Characters extends AppCompatActivity {
    Api api;
    Gson gson;
    RequestQueue requestQueue;
    Answers answers;
    ReadRegisters readRegisters;
    RecyclerView recyclerViewCharacters;
    AdapterCharacters adapterCharacters;
    ArrayList<Results> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        api = new Api();
        answers = new Answers();
        gson = new Gson();
        requestQueue = Volley.newRequestQueue(this);
        readRegisters = new ReadRegisters(this);
        arrayList = new ArrayList<>();
        recyclerViewCharacters = findViewById(R.id.recyclerViewCharacters);
        read();

    }

    private void read(){
        if((api = readRegisters.readUrls()) != null){
            jsonObjet();
        }else{
            Toast.makeText(Characters.this, "no se encontraron ursl", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void jsonObjet() {

        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, api.getCharacters(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                answers = gson.fromJson(response.toString(), Answers.class);
                arrar(answers.getResults());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonRequest);
    }

    private void arrar(ArrayList<Results> results) {
        adapterCharacters = new AdapterCharacters(results,Characters.this);
        recyclerViewCharacters.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCharacters.setAdapter(adapterCharacters);
    }
}