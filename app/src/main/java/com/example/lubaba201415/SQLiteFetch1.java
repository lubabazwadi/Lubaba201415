package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SQLiteFetch1 extends AppCompatActivity {
    LinearLayout studentInfo;
    TextView name, surname, father, nationalID, birthDate, gender;
    EditText idSearchS;
    Button fetch;

    int[] Fid;
    String[] Fname;
    String[] Fsurname;
    String[] Ffather;
    int[] FnationalID;
    String[] FbirthDate;
    String[] Fgender;

    public static int FSQLid;
    public static String FSQLname;
    public static String FSQLsurname;
    public static String FSQLfather;
    public static int FSQLnationalID;
    public static String FSQLbirthDate;
    public static String FSQLgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_fetch_1);

        studentInfo = (LinearLayout) findViewById(R.id.studentInfoS);
        studentInfo.setVisibility(View.INVISIBLE);
        idSearchS = (EditText) findViewById(R.id.idSearchS);
        fetch = (Button) findViewById(R.id.fetchS);
        name = (TextView) findViewById(R.id.SQfname);
        surname = (TextView) findViewById(R.id.SQfsurname);
        father = (TextView) findViewById(R.id.SQffather);
        nationalID = (TextView) findViewById(R.id.SQfnationalID);
        birthDate = (TextView) findViewById(R.id.SQfbirthDate);
        gender = (TextView) findViewById(R.id.SQfgender);
    }

    public void getStudentInfoSQL(View view) {
        String studentid = idSearchS.getText().toString().trim();
        if (!TextUtils.isEmpty(studentid)) {
            StudentSave U = new StudentSave(this);// 1
            // Construct the data source
            ArrayList<StudentSave> studentList = U.getAllStudent(this);
            Fid = new int[studentList.size()];
            Fname = new String[studentList.size()];
            Fsurname = new String[studentList.size()];
            Ffather = new String[studentList.size()];
            FnationalID = new int[studentList.size()];
            FbirthDate = new String[studentList.size()];
            Fgender = new String[studentList.size()];
// 3
            for (int i = 0; i < studentList.size(); i++) {
                StudentSave U1 = studentList.get(i);
                FSQLid = U1.getID();
                String covertIdToString = String.valueOf(FSQLid);
                if (covertIdToString.equals(studentid)) {
                    FSQLname = U1.getName();
                    FSQLsurname = U1.getSurname();
                    FSQLfather = U1.getFather();
                    FSQLnationalID = U1.getNationalID();
                    FSQLbirthDate = U1.getBirthDate();
                    FSQLgender = U1.getGender();
                    break;
                }
            }
            studentInfo.setVisibility(View.VISIBLE);
            name.setText("Name: " + FSQLname);
            surname.setText("Surname: " + FSQLsurname);
            father.setText("Father Name: " + FSQLfather);
            nationalID.setText("National ID: " + FSQLnationalID);
            birthDate.setText("Birth Date: " + FSQLbirthDate);
            gender.setText("Gender: " + FSQLgender);
        }
        else { }
    }
}