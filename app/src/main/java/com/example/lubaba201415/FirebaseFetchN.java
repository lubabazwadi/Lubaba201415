package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.google.firebase.database.*;
import java.util.ArrayList;

public class FirebaseFetchN extends AppCompatActivity {
    public static String GkeyId;
    public static int GID;
    public static String GName;
    public static String GSurname;
    public static String GFather;
    public static int GNationalID;
    public static String GBirthDate;
    public static String GGender;

    DatabaseReference databaseReference;
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<String> keyId = new ArrayList<String>();
    ListView studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_fetch_n);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        studentList = (ListView) findViewById(R.id.studentListF);
        getStudentF();
    }

    class customAdabterActivity extends BaseAdapter {
        public int getCount() {
            return students.size();
        }
        public Object getItem(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }
        public customAdabterActivity() { }
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.custom_student_layout, null);
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView surname = (TextView) view.findViewById(R.id.surname);
            TextView father = (TextView) view.findViewById(R.id.father);
            TextView nationalID = (TextView) view.findViewById(R.id.nationalID);
            TextView birthDate = (TextView) view.findViewById(R.id.birthDate);
            TextView gender = (TextView) view.findViewById(R.id.gender);

            name.setText("Name: " + students.get(position).getName());
            surname.setText("Surname: " + students.get(position).getSurname());
            father.setText("Father Name: " + students.get(position).getFather());
            nationalID.setText("National ID: " + students.get(position).getNationalID());
            birthDate.setText("Gender: " + students.get(position).getGender());
            gender.setText("BirthDate: " + students.get(position).getBirthDate());

            return view;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStudentF();
    }

    public void getStudentF() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                students.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Student newstudent = postSnapshot.getValue(Student.class);
                    keyId.add(postSnapshot.getKey().toString());
                    students.add(newstudent);
                }
                customAdabterActivity theCustomAdabter = new customAdabterActivity();
                studentList.setAdapter(theCustomAdabter);
                studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        GName = students.get(position).getName();
                        GSurname =  students.get(position).getSurname();
                        GID = students.get(position).getID();
                        GBirthDate = students.get(position).getBirthDate();
                        GFather = students.get(position).getFather();
                        GNationalID = students.get(position).getNationalID();
                        GGender = students.get(position).getGender();
                        GkeyId = keyId.get(position);

                        Intent goEdit = new Intent(FirebaseFetchN.this, FirebaseEdit.class);
                        startActivity(goEdit);
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}