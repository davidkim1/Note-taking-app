package com.example.notetaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONException;

public class NewUserActivity extends AppCompatActivity {

    EditText pass, email, firstName, lastName;
    Button newUser, register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        pass = findViewById(R.id.editTextPass);
        email = findViewById(R.id.editTextEmail);
        firstName = findViewById(R.id.editTextName2);
        newUser = findViewById(R.id.newUserButton);
        lastName = findViewById(R.id.lastNameText);
        register = findViewById(R.id.registerButton);

        Server serv = new Server(this);

        newUser.setOnClickListener(view ->
                {
                    if(email.getText().toString().matches("")|| pass.getText().toString().matches("") || firstName.getText().toString().matches("") ||
                            lastName.getText().toString().matches(""))
                    {
                            Toast.makeText(this, "You did not enter a username or password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        try {
                            serv.createAccount(email.getText().toString(), pass.getText().toString(), firstName.getText().toString(),
                                    lastName.getText().toString(), response -> {
                                Log.d("login_app", response+"");
                                try {

                                    Intent intent = new Intent(NewUserActivity.this, RegisterActivity.class);
                                    intent.putExtra("email", email.getText().toString());
                                    intent.putExtra("password", pass.getText().toString());
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
                }
        );

        register.setOnClickListener(v ->{
            Intent intent = new Intent(NewUserActivity.this, RegisterActivity.class);

            NewUserActivity.this.startActivity(intent);
            startActivity(intent);
        });
    }
}
