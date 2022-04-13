package com.aprendizaje.rickandmorty;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.BASEURL;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.IMG_MENU_CHARACTERS;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.IMG_MENU_EPISODES;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.IMG_MENU_FAVORITES;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.IMG_MENU_LOCATIONS;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.NAME_MENU_CHARACTES;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.NAME_MENU_EPISODES;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.NAME_MENU_FAVORITES;
import static com.aprendizaje.rickandmorty.utilidades.Constantes.NAME_MENU_LOCATIONS;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.aprendizaje.rickandmorty.actividades.Characters;
import com.aprendizaje.rickandmorty.actividades.Episodes;
import com.aprendizaje.rickandmorty.actividades.Locations;
import com.aprendizaje.rickandmorty.adaptadores.MenuAdapter;
import com.aprendizaje.rickandmorty.database.InsertRegisters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.AnswersCharacters;
import com.aprendizaje.rickandmorty.modelos.AnswersEpisodes;
import com.aprendizaje.rickandmorty.modelos.AnswersLocations;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.aprendizaje.rickandmorty.modelos.Character;
import com.aprendizaje.rickandmorty.modelos.Episode;
import com.aprendizaje.rickandmorty.modelos.Info;
import com.aprendizaje.rickandmorty.modelos.Location;
import com.aprendizaje.rickandmorty.modelos.Menu;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CallbackMenu {
    RequestQueue requestQueue;
    TextView verTexto;
    public static Info info = null;
    public static Api api = null;
    public static AnswersCharacters answersCharacters = null;
    public static Character characterModel = null;
    public static AnswersLocations answersLocations = null;
    public static Location locationModel = null;
    public static AnswersEpisodes answersEpisodes = null;
    public static Episode episodeModel = null;
    Gson gson;
    InsertRegisters insertRegisters;
    ReadRegisters readRegisters;
    RecyclerView recyclerViewMenu;
    ArrayList<Menu> listMenu;
    MenuAdapter menuAdapter;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // stringRequest();
        //jsonArrayRequest();



        if (readRegisters.readUrls() == null) {
            runOnUiThread(() -> getData(BASEURL, "base"));
            Toast.makeText(MainActivity.this, "No estan los registros", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Ya estan los registros", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void clickListener(Menu menu) {
        //   Toast.makeText(this, "hola name"+ menu.getName(), Toast.LENGTH_SHORT).show();
        switch (menu.getName()) {
            case NAME_MENU_CHARACTES:
                startActivity(new Intent(this, Characters.class));
                break;
            case NAME_MENU_LOCATIONS:
                startActivity(new Intent(this, Locations.class));
                break;
            case NAME_MENU_EPISODES:
                startActivity(new Intent(this, Episodes.class));
                break;
            case NAME_MENU_FAVORITES:
                // startActivity(new Intent(this, Characters.class));
                break;
        }

    }

    private void init() {
        requestQueue = Volley.newRequestQueue(this);
        api = Api.getInstance();
        gson = new Gson();
        insertRegisters = new InsertRegisters(this);
        readRegisters = new ReadRegisters(this);
        recyclerViewMenu = findViewById(R.id.recyclerViewMenu);
        listMenu = new ArrayList<>();
        listMenu.add(new Menu(IMG_MENU_CHARACTERS, NAME_MENU_CHARACTES));
        listMenu.add(new Menu(IMG_MENU_LOCATIONS, NAME_MENU_LOCATIONS));
        listMenu.add(new Menu(IMG_MENU_EPISODES, NAME_MENU_EPISODES));
        listMenu.add(new Menu(IMG_MENU_FAVORITES, NAME_MENU_FAVORITES));
        menuAdapter = new MenuAdapter(MainActivity.this, listMenu, MainActivity.this::clickListener);
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
                if (insertRegisters.insertUrls(api) > 0) {
                    Toast.makeText(MainActivity.this, "Se Agregaron urls", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error al agregar urls", Toast.LENGTH_SHORT).show();
                }
            }
        }, error -> verTexto.setText("error en la url"));

        requestQueue.add(jsonRequest);
    }

    /**
     *
     * @param url la que descargara 
     * @param type typo de descarga
     */
    private void getData(String url, String type) {
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            switch (type) {
                case "base":
                    api = gson.fromJson(response.toString(), Api.class);
                    insertRegisters.insertUrls(api);
                    getData(api.getCharacters(), "character");
                    break;
                case "character":
                    answersCharacters = gson.fromJson(response.toString(), AnswersCharacters.class);
                    insertRegisters.insertCharacter(answersCharacters.getResults());
                    if (answersCharacters.getInfo().getNext() != null)
                        getData(answersCharacters.getInfo().getNext(), "character");
                    else
                        getData(api.getLocations(), "locations");
                    break;
                case "locations":
                    answersLocations = gson.fromJson(response.toString(), AnswersLocations.class);
                    insertRegisters.insertLocation(answersLocations.getResults());
                    if (answersLocations.getInfo().getNext() != null)
                        getData(answersLocations.getInfo().getNext(), "locations");
                    else
                        getData(api.getEpisodes(), "episodes");
                    break;
                case "episodes":
                    answersEpisodes = gson.fromJson(response.toString(), AnswersEpisodes.class);
                    insertRegisters.insertEpisodes(answersEpisodes.getResults());
                    if (answersEpisodes.getInfo().getNext() != null)
                        getData(answersEpisodes.getInfo().getNext(), "episodes");
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Insertado completado", Toast.LENGTH_SHORT).show();
            }
        }, error -> verTexto.setText("error en la url"));
        requestQueue.add(jsonRequest);
    }


}