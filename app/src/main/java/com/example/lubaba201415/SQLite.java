package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.*;

public class SQLite extends AppCompatActivity {
    DatabaseReference reference;
    StudentSave studentSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        reference = FirebaseDatabase.getInstance().getReference("students");
        studentSave = new StudentSave(this);

        Button fetch1 = (Button) findViewById(R.id.fetch1);
        Button fetchn = (Button) findViewById(R.id.fetchn);

        fetch1.setOnClickListener(e-> startActivity(new Intent(SQLite.this, SQLiteFetch1.class)));
        fetchn.setOnClickListener(e-> startActivity(new Intent(SQLite.this, SQLiteFetchN.class)));
    }

    public void fireToSQL(View view) {
        view.setEnabled(false);
        fireToSQL2();
        Toast.makeText(getBaseContext(), "Please wait", Toast.LENGTH_SHORT).show();
    }

    public void fireToSQL2() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Student newstudent = postSnapshot.getValue(Student.class);
                    studentSave.addStudent((new StudentSave(newstudent.getName(), newstudent.getSurname(), newstudent.getFather(), newstudent.getNationalID(), newstudent.getBirthDate(), newstudent.getGender(), SQLite.this)));
                }
                Toast.makeText(getBaseContext(), "Students added successfully to SQLite ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}