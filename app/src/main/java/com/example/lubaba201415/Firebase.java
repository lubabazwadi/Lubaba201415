package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Firebase extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        Button addf=(Button)findViewById(R.id.addf);
        Button fetch1f=(Button)findViewById(R.id.fetch1f);
        Button fetchnf=(Button)findViewById(R.id.fetchnf);

        addf.setOnClickListener(e->startActivity(new Intent(Firebase.this, FirebaseAdd.class)));
        fetch1f.setOnClickListener(e->startActivity(new Intent(Firebase.this, FirebaseFetch1.class)));
        fetchnf.setOnClickListener(e->startActivity(new Intent(Firebase.this, FirebaseFetchN.class)));
    }
}