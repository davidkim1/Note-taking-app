package com.example.notetaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class ShareActivity extends AppCompatActivity {

    EditText emailShare , docId;
    Button share;
    Server serv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        emailShare = findViewById(R.id.emailShare);
        share = findViewById(R.id.buttonShare);
        serv = new Server(this);
        docId = findViewById(R.id.docId);

        share.setOnClickListener(v -> {
            try {
                serv.setDocumentAccessors(emailShare.getText().toString(), docId.getText().toString(),response -> {
                    Log.d("login_app", response+"");
                    try {
                        Intent intent = new Intent(ShareActivity.this, DocumentsActiviy.class);
                        startActivity(intent);

                    }catch (Exception e){};
                }, error -> {
                    Toast.makeText(this, "Could not share", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
}
