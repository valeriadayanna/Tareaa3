package com.example.tareaa3;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText txtUser;
    EditText txtPass;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPassword);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void eventoButtonVolley(View v) {
        login();
    }

    private void login() {
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET,
                "https://raw.githubusercontent.com/Ariel2802/Tarea1/master/MiJSON.json",
                null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int tamanio = response.length();
                boolean noExiste = true;

                for (int i = 0; i < tamanio; i++) {
                    try {
                        JSONObject json = new JSONObject(response.get(i).toString());
                        Usuario usuario = new Usuario(json.getString("user"),
                                json.getString("password"), json.getString("photo"),
                                json.getString("roleid"));
                        if (txtUser.getText().toString().equalsIgnoreCase(usuario.getUser()) &&
                                txtPass.getText().toString().equals(usuario.getPassword())) {
                            noExiste = false;
                            Intent intent=new Intent(MainActivity.this, LytInicio.class);
                            Bundle bundle =new Bundle();
                            bundle.putSerializable("Usuario",usuario);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        }
                    } catch (JSONException ex) {
                        mostrarMensaje("Error");
                        System.out.println(ex.toString());
                    }
                }
                if (noExiste) {
                    mostrarMensaje("No existe");
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError ex) {
                mostrarMensaje("Error");
                System.out.println(ex.toString());
            }
        });
        requestQueue.add(jsonRequest);

        /* .baseUrl("https://revistas.uteq.edu.ec/ws/")*/
    }

    private void mostrarMensaje(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}