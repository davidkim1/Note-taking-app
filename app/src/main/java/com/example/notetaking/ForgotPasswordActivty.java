package com.example.notetaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class ForgotPasswordActivty extends AppCompatActivity {

    EditText em, TP, newP;
    Button reset;
    Server serv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_activty);

        em = findViewById(R.id.emil);
        TP = findViewById(R.id.tempP);
        newP = findViewById(R.id.newP);
        reset = findViewById(R.id.SetPbutton);
        serv = new Server(this);

        Intent myIntent = getIntent();

        em.setText(myIntent.getStringExtra("email"));

        reset.setOnClickListener(v -> {
            try {
                serv.registerAccount(myIntent.getStringExtra("email"), newP.getText().toString(),
                        TP.getText().toString(), response -> {
                            Log.d("login_app", response+"");
                            try {
                                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgotPasswordActivty.this, MainActivity.class);
                                startActivity(intent);

                            }catch (Exception e){};
                        }, error -> {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
}

