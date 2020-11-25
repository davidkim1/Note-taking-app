package com.example.notetaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class DeleteActivity extends AppCompatActivity {

    EditText deleteId;
    Button deleteButton;
    Server serv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deleteId = findViewById(R.id.deleteid);
        deleteButton = findViewById(R.id.deleteButton);
        serv = new Server(this);

        deleteButton.setOnClickListener(v -> {
            try {
                serv.deleteDoc(response -> {
                    Log.d("login_app", response+"");
                }, error -> {
                    Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
}
