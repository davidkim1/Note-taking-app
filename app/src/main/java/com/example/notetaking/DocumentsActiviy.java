package com.example.notetaking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class DocumentsActiviy extends AppCompatActivity {

    EditText title;
    EditText content;
    String username;
    Button creatDocument;
    //DocumentPojo doc;
    Map<String, String> doc = new HashMap();
    String randomId;
    Server serv;
    Request request;

    public static final String TOKEN_ID = DocumentsActiviy.class.getCanonicalName()+"TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents_activiy);

        title = findViewById(R.id.editText6);
        content = findViewById(R.id.editText8);
        creatDocument = findViewById(R.id.createDocument);

        serv = new Server(this);
        randomId = UUID.randomUUID().toString();
        //doc = new DocumentPojo();

        creatDocument.setOnClickListener(v -> {
            Intent reset = new Intent(DocumentsActiviy.this, ResetPassword.class);
            startActivity(reset);
        });
        Intent myIntent = getIntent();
        username = myIntent.getStringExtra("email");
        System.out.println("\n\n\n\n\n" + username + "\n\n\n\n\n\n");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.delete:
                Intent Dintent = new Intent(DocumentsActiviy.this, DeleteActivity.class);
                startActivity(Dintent);
                return true;
            case R.id.save:
                    save();
                return true;
            case R.id.login:
                Intent intent = new Intent(DocumentsActiviy.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.settings:
                Intent setIntent = new Intent(DocumentsActiviy.this, SetingsActivity.class);
                startActivity(setIntent);
                return true;
            case R.id.load:
                Intent loadPage = new Intent(DocumentsActiviy.this, LoadActivty.class);
                startActivity(loadPage);
                return true;
            case R.id.share:
                Intent in = new Intent(DocumentsActiviy.this, ShareActivity.class);
                startActivity(in);
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    public void save()
    {
        if(title.getText().toString().matches("")|| content.getText().toString().matches(""))
        {
            Toast.makeText(this, "You did not enter a title or text", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try {
                request = serv.setDocument(UUID.randomUUID().toString(),content.getText().toString(), title.getText().toString(),System.currentTimeMillis()+"", response -> {
                    Log.d("login_app", response+"");
                    Toast.makeText(this, "Document Set Successfully", Toast.LENGTH_SHORT).show();
                }, error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
