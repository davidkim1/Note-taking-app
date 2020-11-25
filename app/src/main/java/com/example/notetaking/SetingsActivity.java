package com.example.notetaking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.widget.Button;

public class SetingsActivity extends AppCompatActivity {

    Button theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);

        theme = findViewById(R.id.changeTheme);

        theme.setOnClickListener(view ->
                {
                    setTheme(R.style.BlackTheme);
                    setContentView(R.layout.activity_setings);
                }
        );
    }
}
