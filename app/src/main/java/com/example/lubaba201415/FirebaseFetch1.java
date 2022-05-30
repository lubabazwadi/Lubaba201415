package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.firebase.database.*;

public class FirebaseFetch1 extends AppCompatActivity {
    LinearLayout studentInfo;
    TextView name, surname, father, nationalID, birthDate, gender;
    EditText studentId;
    Button fetch;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_fetch_1);

        studentInfo = (LinearLayout) findViewById(R.id.studentInfo);
        studentInfo.setVisibility(View.INVISIBLE);
        studentId = (EditText) findViewById(R.id.studentId);
        fetch = (Button) findViewById(R.id.fetch);
        name = (TextView) findViewById(R.id.fname1);
        surname = (TextView) findViewById(R.id.fsurname1);
        father = (TextView) findViewById(R.id.ffather1);
        nationalID = (TextView) findViewById(R.id.fnationalID1);
        birthDate = (TextView) findViewById(R.id.fbirthDate1);
        gender = (TextView) findViewById(R.id.fgender1);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");
    }

    public void getStudent(View view) {
        String studentid = studentId.getText().toString().trim();
        if (!TextUtils.isEmpty(studentid)) {
            Toast.makeText(FirebaseFetch1.this, "Please Wait", Toast.LENGTH_LONG).show();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Student newstudent = postSnapshot.getValue(Student.class);
                        Log.i("getStudent",newstudent.getID()+"");

                        String sid = String.valueOf(newstudent.getID());
                        if(studentid.equals(sid)) {
                            studentInfo.setVisibility(View.VISIBLE);
                            name.setText("Name: "+newstudent.getName());
                            surname.setText("Surname: "+newstudent.getSurname());
                            father.setText("Father Name: "+newstudent.getFather());
                            nationalID.setText("National ID: "+newstudent.getNationalID());
                            birthDate.setText("BirthDate: "+newstudent.getBirthDate());
                            gender.setText("Gender: "+newstudent.getGender());
                            break;
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) { }
            });
        }
    }
}