package com.aprendizaje.rickandmorty.actividades;

import static com.aprendizaje.rickandmorty.MainActivity.characterModel;
import static com.aprendizaje.rickandmorty.MainActivity.episodeModel;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.adaptadores.AdapterCharacters;
import com.aprendizaje.rickandmorty.adaptadores.AdapterCharactersEpisode;
import com.aprendizaje.rickandmorty.database.InsertRegisters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.Character;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class DataCharacter extends AppCompatActivity {

    AdapterCharactersEpisode adapterCharactersEpisode;
    AdapterCharacters adapterCharacters;
    RecyclerView recyclerViewEpisodes;
    RecyclerView recyclerViewCharacters;
    int id ;
    Gson gson;
    RequestQueue requestQueue;
    InsertRegisters insertRegisters;
    ReadRegisters readRegisters;
    ArrayList<Character> listCharacter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_character);
        recyclerViewEpisodes = findViewById(R.id.recyclerViewEpisodes);
        recyclerViewCharacters = findViewById(R.id.recyclerViewCharacters);
        listCharacter = new ArrayList<>();
        gson = new Gson();
        insertRegisters = new InsertRegisters(this);
        readRegisters = new ReadRegisters(this);
        requestQueue = Volley.newRequestQueue(this);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("ID");
        listCharacter = readRegisters.readCharacter(id, id, "character");
        arrar(listCharacter.get(0).getEpisode());
    }
    private void arrar(ArrayList<String> character) {
        adapterCharactersEpisode = new AdapterCharactersEpisode(character, DataCharacter.this);
        recyclerViewEpisodes.setLayoutManager(new GridLayoutManager(this,3));
        recyclerViewEpisodes.setAdapter(adapterCharactersEpisode);
        adapterCharacters = new AdapterCharacters(listCharacter, DataCharacter.this);
        recyclerViewCharacters.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCharacters.setAdapter(adapterCharacters);

    }
}