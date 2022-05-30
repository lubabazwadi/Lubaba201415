package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseEdit extends AppCompatActivity {
    Button edit, delete, add1;
    EditText name, surname, father, nationalID, birthDate, gender;
    DatabaseReference databaseReference;
    StudentSave student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_edit);

        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);
        add1 = (Button) findViewById(R.id.add1);

        name = (EditText) findViewById(R.id.name2);
        surname = (EditText) findViewById(R.id.surname2);
        father = (EditText) findViewById(R.id.father2);
        nationalID = (EditText) findViewById(R.id.nationalID2);
        birthDate = (EditText) findViewById(R.id.birthDate2);
        gender = (EditText) findViewById(R.id.gender2);

        name.setText(FirebaseFetchN.GName);
        surname.setText(FirebaseFetchN.GSurname);
        father.setText(FirebaseFetchN.GFather);
        nationalID.setText(FirebaseFetchN.GNationalID);
        birthDate.setText(FirebaseFetchN.GBirthDate);
        gender.setText(FirebaseFetchN.GGender);
        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        student = new StudentSave(this);

        add1.setOnClickListener(e-> {
            student.addStudent((new StudentSave(name.getText().toString(), surname.getText().toString(), father.getText().toString(), Integer.parseInt(nationalID.getText().toString()), birthDate.getText().toString(), gender.getText().toString(), FirebaseEdit.this)));
            Toast.makeText(getBaseContext(), "student added to SQLite successfully", Toast.LENGTH_SHORT).show();
            finish();
        });

        edit.setOnClickListener(e-> {
            updateStudent(FirebaseFetchN.GkeyId, name.getText().toString(), surname.getText().toString(), father.getText().toString(), Integer.parseInt(nationalID.getText().toString()), birthDate.getText().toString(), gender.getText().toString());
            Toast.makeText(getBaseContext(), "student updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        });

        delete.setOnClickListener(e-> {
            deleteStudent(FirebaseFetchN.GkeyId);
            Toast.makeText(getBaseContext(), "student deleted successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void deleteStudent(String key2delet) { databaseReference.child(key2delet).removeValue(); }

    public void updateStudent(String key2update, String name, String surname, String father, int nationalID, String birthDate, String gender) {
        databaseReference.child(key2update).child("name").setValue(name);
        databaseReference.child(key2update).child("surname").setValue(surname);
        databaseReference.child(key2update).child("father").setValue(father);
        databaseReference.child(key2update).child("nationalID").setValue(nationalID);
        databaseReference.child(key2update).child("birthDate").setValue(birthDate);
        databaseReference.child(key2update).child("gender").setValue(gender);
    }
}