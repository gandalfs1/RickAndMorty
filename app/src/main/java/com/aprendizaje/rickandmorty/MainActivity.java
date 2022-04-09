package com.aprendizaje.rickandmorty;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.BASEURL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.aprendizaje.rickandmorty.database.InsertRegisters;
import com.aprendizaje.rickandmorty.database.ReadRegisters;
import com.aprendizaje.rickandmorty.modelos.Api;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button click;
    Button click2;
    RequestQueue requestQueue;
    TextView verTexto;
    Api api;
    Gson gson;
    InsertRegisters insertRegisters;
    ReadRegisters readRegisters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click = findViewById(R.id.clik);
        click2 = findViewById(R.id.clik2);
        verTexto = findViewById(R.id.verTexto);
        requestQueue = Volley.newRequestQueue(this);
        api = new Api();
        gson = new Gson();
        insertRegisters = new InsertRegisters(this);
        readRegisters = new ReadRegisters(this);
        // stringRequest();
        //jsonArrayRequest();
        if((api = readRegisters.readUrls()) == null){
            jsonObjet();
        }else{
            Toast.makeText(MainActivity.this, "Ya estan los registros", Toast.LENGTH_SHORT).show();
        }
        click2.setOnClickListener(v -> {
            verTexto.setText("vacio");
        });

        click.setOnClickListener(v -> {
            startActivity(new Intent(this, Characters.class));
        });
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

    private void jsonObjet() {
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, BASEURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                api = gson.fromJson(response.toString(), Api.class);
                verTexto.setText(response.toString());
                if (insertRegisters.insertUrls(api)>0){
                    Toast.makeText(MainActivity.this, "Se Agregaron urls", Toast.LENGTH_SHORT).show();
                }else {
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
}