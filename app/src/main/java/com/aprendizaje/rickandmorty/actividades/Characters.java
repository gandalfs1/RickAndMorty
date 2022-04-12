package com.aprendizaje.rickandmorty.actividades;

import static com.aprendizaje.rickandmorty.MainActivity.*;

import androidx.appcompat.app.AppCompatActivity;
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
import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.adaptadores.AdapterCharacters;
import com.aprendizaje.rickandmorty.database.InsertRegisters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.AnswersCharacters;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.aprendizaje.rickandmorty.modelos.Character;
import com.aprendizaje.rickandmorty.modelos.Info;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class Characters extends AppCompatActivity {

    Gson gson;
    RequestQueue requestQueue;
    ReadRegisters readRegisters;
    RecyclerView recyclerViewCharacters;
    AdapterCharacters adapterCharacters;
    ArrayList<Character> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        api = Api.getInstance();
        answersCharacters = AnswersCharacters.getInstance();
        gson = new Gson();
        requestQueue = Volley.newRequestQueue(this);
        readRegisters = new ReadRegisters(this);
        arrayList = new ArrayList<>();
        recyclerViewCharacters = findViewById(R.id.recyclerViewCharacters);
        runOnUiThread(() -> read());
    }

    private void read(){
       arrar(readRegisters.readCharacter());
    }

    private void arrar(ArrayList<Character> results) {
        adapterCharacters = new AdapterCharacters(results,Characters.this);
        recyclerViewCharacters.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCharacters.setAdapter(adapterCharacters);
    }
}