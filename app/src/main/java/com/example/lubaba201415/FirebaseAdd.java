package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.Collections;

public class FirebaseAdd extends AppCompatActivity {
    EditText addName, addSurname, addFather, addNationalID, addBirthDate, addGender;
    Button add;
    DatabaseReference databaseReference;
    ArrayList<Integer> getAllKey = new ArrayList<Integer>();
    Boolean stopGetData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_add);

        addName = (EditText) findViewById(R.id.addName);
        addSurname = (EditText) findViewById(R.id.addSurname);
        addFather = (EditText) findViewById(R.id.addFather);
        addNationalID = (EditText) findViewById(R.id.addNationalID);
        addBirthDate = (EditText) findViewById(R.id.addBirthDate);
        addGender = (EditText) findViewById(R.id.addGender);
        add = (Button) findViewById(R.id.add);
    }

    public void addStudentF(View view) {
        String name_add = addName.getText().toString().trim();
        String surname_add = addSurname.getText().toString().trim();
        String father_add = addFather.getText().toString().trim();
        String nationalID_add = addNationalID.getText().toString().trim();
        String birthDate_add = addBirthDate.getText().toString().trim();
        String gender_add = addGender.getText().toString().trim();

        if (!TextUtils.isEmpty(name_add) && !TextUtils.isEmpty(surname_add) && !TextUtils.isEmpty(father_add) && !TextUtils.isEmpty(nationalID_add) && !TextUtils.isEmpty(birthDate_add) && !TextUtils.isEmpty(gender_add)) {
            databaseReference = FirebaseDatabase.getInstance().getReference("students");
            Toast.makeText(FirebaseAdd.this, "Please Wait", Toast.LENGTH_LONG).show();
            add.setEnabled(false);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (stopGetData == false) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Student newstudent = postSnapshot.getValue(Student.class);
                            int getKey = Integer.valueOf(postSnapshot.getKey());
                            getAllKey.add(getKey);
                        }
                        int getMaxValue = Collections.max(getAllKey);
                        Log.i("max", getMaxValue + "");
                        getMaxValue++;
                        String newKeyIncremnt = String.valueOf(getMaxValue);
                        Log.i("new", newKeyIncremnt + "");
                        Toast.makeText(FirebaseAdd.this, "students added successfully", Toast.LENGTH_LONG).show();

                        databaseReference.child(newKeyIncremnt).child("name").setValue(name_add);
                        databaseReference.child(newKeyIncremnt).child("surname").setValue(surname_add);
                        databaseReference.child(newKeyIncremnt).child("father").setValue(father_add);
                        databaseReference.child(newKeyIncremnt).child("nationalID").setValue(nationalID_add);
                        databaseReference.child(newKeyIncremnt).child("birthDate").setValue(birthDate_add);
                        databaseReference.child(newKeyIncremnt).child("gender").setValue(gender_add);
                        databaseReference.child(newKeyIncremnt).child("id").setValue(getMaxValue + 1);
                        stopGetData = true;
                        finish();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) { }
            });
        } else
            Toast.makeText(FirebaseAdd.this, "Please enter all data", Toast.LENGTH_LONG).show();
    }
}