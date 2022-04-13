package com.aprendizaje.rickandmorty.actividades;

import static com.aprendizaje.rickandmorty.MainActivity.answersCharacters;
import static com.aprendizaje.rickandmorty.MainActivity.api;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.adaptadores.AdapterCharacters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.AnswersCharacters;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.aprendizaje.rickandmorty.modelos.Character;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Characters extends AppCompatActivity {

    Gson gson;
    RequestQueue requestQueue;
    ReadRegisters readRegisters;
    RecyclerView recyclerViewCharacters;
    AdapterCharacters adapterCharacters;
    ArrayList<Character> arrayList;
    int prev = 1;
    int next = 20;
    String type = "character";
    BottomNavigationView bottomNavigationView;

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
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        runOnUiThread(() -> read(prev, next));

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.prev:

                        if (prev != 1) {
                            prev = prev - 20;
                            next = next - 20;
                            read(prev, next);
                        } else {
                            Toast.makeText(Characters.this, "this is first page ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.netx:
                        if (next < 826) {
                            prev = prev + 20;
                            next = next + 20;
                            read(prev, next);
                        } else {
                            Toast.makeText(Characters.this, "this is last page ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return false;
            }
        });
    }


    private void read(int star, int end) {
        arrar(readRegisters.readCharacter(star, end, type));
    }

    private void arrar(ArrayList<Character> results) {
        adapterCharacters = new AdapterCharacters(results, Characters.this);
        recyclerViewCharacters.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCharacters.setAdapter(adapterCharacters);
    }
}