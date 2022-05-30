package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SQLiteFetchN extends AppCompatActivity {
    ListView studentList;

    int[] id;
    String[] name;
    String[] surname;
    String[] father;
    int[] nationalID;
    String[] birthDate;
    String[] gender;

    public static int SQLposition;
    public static int SQLid;
    public static String SQLName;
    public static String SQLSurname;
    public static String SQLfather;
    public static int SQLnationalID;
    public static String SQLbirthDate;
    public static String SQLgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_fetch_n);

        studentList = (ListView) findViewById(R.id.studentListS);
        StudentSave U = new StudentSave(this);
// 1
        ArrayList<StudentSave> studentSaveList = U.getAllStudent(this);
        id = new int[studentSaveList.size()];
        name = new String[studentSaveList.size()];
        surname = new String[studentSaveList.size()];
        father = new String[studentSaveList.size()];
        nationalID = new int[studentSaveList.size()];
        birthDate = new String[studentSaveList.size()];
        gender = new String[studentSaveList.size()];
// 3
        for (int i = 0; i < studentSaveList.size(); i++) {
            StudentSave U1 = studentSaveList.get(i);
            id[i] = U1.getID();
            name[i] = U1.getName();
            surname[i] = U1.getSurname();
            father[i] = U1.getFather();
            nationalID[i] = U1.getNationalID();
            birthDate[i] = U1.getBirthDate();
            gender[i] = U1.getGender();
        }

        customAdabterActivity theCustomAdabter = new customAdabterActivity();
        studentList.setAdapter(theCustomAdabter);

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SQLposition = position;
                SQLid = SQLiteFetchN.this.id[position];
                SQLName = name[position];
                SQLSurname = surname[position];
                SQLfather = father[position];
                SQLnationalID = nationalID[position];
                SQLbirthDate = birthDate[position];
                SQLgender = gender[position];
                Log.i("SQLITEstudentId", SQLid + "");

                finish();
                Intent goEdit = new Intent(SQLiteFetchN.this, SQLiteEdit.class);
                startActivity(goEdit);
            }
        });
    }

    class customAdabterActivity extends BaseAdapter {
        @Override
        public int getCount() {
            return name.length;
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
            TextView firstNametext = (TextView) view.findViewById(R.id.name);
            TextView lastNametext = (TextView) view.findViewById(R.id.surname);
            TextView phoneNumbertext = (TextView) view.findViewById(R.id.gender);
            TextView emailAddressText = (TextView) view.findViewById(R.id.birthDate);

            firstNametext.setText("First Name: " + name[position]);
            lastNametext.setText("Last Name: " + surname[position]);
            phoneNumbertext.setText("Phone Number: " + birthDate[position]);
            emailAddressText.setText("Email Address: " + gender[position]);
            return view;
        }
    }
}