package com.example.notetaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class ResetPassword extends AppCompatActivity {

    EditText whatemail;
    Button reset;
    Server serv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        whatemail = findViewById(R.id.whatEmail);
        reset = findViewById(R.id.resBut);

        serv = new Server(this);

        reset.setOnClickListener(v -> {
            try {

            serv.forgotPassword(whatemail.getText().toString(), response -> {
                Intent intent = new Intent(ResetPassword.this, ForgotPasswordActivty.class);
                intent.putExtra("email", whatemail.getText().toString());
                startActivity(intent);
            }, error -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }});
    }
}
