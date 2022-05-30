package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button firebase=(Button)findViewById(R.id.firebase);
        Button SQLite=(Button)findViewById(R.id.SQLite);
        Button Weather=(Button)findViewById(R.id.Weather);

        firebase.setOnClickListener(e->startActivity(new Intent(MainActivity.this, Firebase.class)));
        SQLite.setOnClickListener(e->startActivity(new Intent(MainActivity.this, SQLite.class)));
        Weather.setOnClickListener(e->startActivity(new Intent(MainActivity.this, Weather1.class)));
    }
}