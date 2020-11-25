package com.example.notetaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText pass;
    Button signUp;
    Button forgotP;
    Button newUser;
    public static final String TOKEN_ID = MainActivity.class.getCanonicalName()+"TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editText3);
        pass = findViewById(R.id.editText2);
        signUp = findViewById(R.id.signInButton);
        forgotP = findViewById(R.id.forgotPbutton);
        newUser = findViewById(R.id.newUserButton);

        Server serv = new Server(this);

        signUp.setOnClickListener(view ->
                    {
                        if(email.getText().toString().matches("")|| pass.getText().toString().matches(""))
                        {
                            Toast.makeText(this, "You did not enter a username or password", Toast.LENGTH_SHORT).show();
                        }
                        try {
                            Request request = serv.authenticate(email.getText().toString(),pass.getText().toString(), response -> {
                                Log.d("login_app", response+"");
                                try {
                                    serv.setToken(response.getString("token"));
                                    Intent intent = new Intent(MainActivity.this, DocumentsActiviy.class);
                                    Log.d("login_app", response.get("token").toString());
                                    startActivity(intent);

                                }catch (Exception e){};
                            }, error -> {
                                Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
            );
        forgotP.setOnClickListener(view ->
                {
                    Intent intent = new Intent(MainActivity.this, ForgotPasswordActivty.class);
                    startActivity(intent);
                }
        );
        newUser.setOnClickListener(view ->
                {
                    Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
                    startActivity(intent);
                }
        );
    }
}
