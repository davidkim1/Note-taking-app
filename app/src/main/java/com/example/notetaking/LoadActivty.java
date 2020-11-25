package com.example.notetaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoadActivty extends AppCompatActivity {

    EditText fileTitle;
    EditText loadedText;
    Button load;
    Server serv;
    String text;
    Request req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_activty);

        loadedText = findViewById(R.id.loadedText);
        load = findViewById(R.id.button2);
        serv = new Server(this);

        load.setOnClickListener(view ->
                {
                    try {
                         serv.getDocument(response -> {
                            Log.d("login_app", response+"\n////////////////////////////////////////////////////////////");
                                //loadedText.setText(response.getJSONArray("scope").toString());
                                //response.getJSONArray("getDocuments");
                                /*JSONArray jsonarray = new JSONArray(response);
                                for(int i=0; i < jsonarray.length(); i++) {
                                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                                    String id       = jsonobject.getString("id");
                                    text    = jsonobject.getString("text");
                                    String creation_date  = jsonobject.getString("creation_date");
                                    String title = jsonobject.getString("title");
                                }

                                loadedText.setText(text);*/
                             try {
                                 loadedText.setText("ID: " + response.getString("id") + "\n\n" + "TITLE: " + response.getString("title") + "\n\n\nTEXT:\n" + response.getString("text"));
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                         }, error -> {
                            Toast.makeText(this, "Couldnt Load", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

        );


    }


}
