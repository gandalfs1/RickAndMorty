package com.aprendizaje.rickandmorty;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.*;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aprendizaje.rickandmorty.adaptadores.MenuAdapter;
import com.aprendizaje.rickandmorty.database.InsertRegisters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.AnswersCharacters;
import com.aprendizaje.rickandmorty.modelos.AnswersEpisodes;
import com.aprendizaje.rickandmorty.modelos.AnswersLocations;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.aprendizaje.rickandmorty.modelos.Menu;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CallbackMenu {
    Button click;
    Button click2;
    RequestQueue requestQueue;
    TextView verTexto;
    Api api;
    AnswersCharacters answersCharacters;
    AnswersLocations answersLocations;
    AnswersEpisodes answersEpisodes;
    Gson gson;
    InsertRegisters insertRegisters;
    ReadRegisters readRegisters;
    RecyclerView recyclerViewMenu;
    ArrayList<Menu> listMenu;
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // stringRequest();
        //jsonArrayRequest();
        //if((api = readRegisters.readUrls()) == null){
        // getUrls();
       /* }else{
            Toast.makeText(MainActivity.this, "Ya estan los registros", Toast.LENGTH_SHORT).show();
        }*/


        // startActivity(new Intent(this, Characters.class));

    }

    @Override
    public void clickListener(Menu menu) {

    }

    private void init() {
        requestQueue = Volley.newRequestQueue(this);
        api = new Api();
        gson = new Gson();
        insertRegisters = new InsertRegisters(this);
        readRegisters = new ReadRegisters(this);
        answersCharacters = new AnswersCharacters();
        answersLocations = new AnswersLocations();
        answersEpisodes = new AnswersEpisodes();
        recyclerViewMenu = findViewById(R.id.recyclerViewMenu);
        listMenu = new ArrayList<>();
        listMenu.add(new Menu(IMG_MENU_CHARACTERS, "CHARACTERS"));
        listMenu.add(new Menu(IMG_MENU_LOCATIONS, "LOCATIONS"));
        listMenu.add(new Menu(IMG_MENU_EPISODES, "EPISODES"));
        listMenu.add(new Menu(IMG_MENU_FAVORITES, "FAVORITES"));
        menuAdapter = new MenuAdapter(MainActivity.this, listMenu ,MainActivity.this::clickListener);
        recyclerViewMenu.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        recyclerViewMenu.setAdapter(menuAdapter);
    }

    private void stringRequest() {
        StringRequest request = new StringRequest(Request.Method.GET, BASEURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                verTexto.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
    }

    private void jsonArrayRequest() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASEURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int size = response.length();
                for (int i = 0; i < size; i++) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.get(i).toString());
                        String tittle = jsonObject.getString("locations");
                        verTexto.setText(tittle);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void getUrls() {
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, BASEURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                api = gson.fromJson(response.toString(), Api.class);
                // verTexto.setText(response.toString());
                if (insertRegisters.insertUrls(api) > 0) {
                    Toast.makeText(MainActivity.this, "Se Agregaron urls", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error al agregar urls", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                verTexto.setText("error en la url");
            }
        });

        requestQueue.add(jsonRequest);
    }

    private void getData(String url, String type) {
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                switch (type) {
                    case "character":
                        answersCharacters = gson.fromJson(response.toString(), AnswersCharacters.class);
                        break;
                    case "locations":
                        answersLocations = gson.fromJson(response.toString(), AnswersLocations.class);
                        break;
                    case "episodes":
                        answersEpisodes = gson.fromJson(response.toString(), AnswersEpisodes.class);
                        break;
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                verTexto.setText("error en la url");
            }
        });

        requestQueue.add(jsonRequest);
    }


}