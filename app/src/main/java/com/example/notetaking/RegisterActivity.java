package com.example.notetaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity {

    Button finishReg;
    EditText tempPass;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        finishReg = findViewById(R.id.finishRegistration);
        tempPass = findViewById(R.id.tempPassField);
        text = findViewById(R.id.textView5);

        Server serv = new Server(this);

        finishReg.setOnClickListener(view -> {
            if (tempPass.getText().toString().matches(""))
            {
                Toast.makeText(this, "Please enter you temporary pass.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent myIntent = getIntent();
                try {
                    serv.registerAccount(myIntent.getStringExtra("email"), myIntent.getStringExtra("password"),
                            tempPass.getText().toString(), response -> {
                                Log.d("login_app", response+"");
                                try {

                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);

                                }catch (Exception e){};
                            }, error -> {
                                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
