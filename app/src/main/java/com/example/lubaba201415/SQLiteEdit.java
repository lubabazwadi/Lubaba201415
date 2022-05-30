package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;

public class SQLiteEdit extends AppCompatActivity {
    Button edit, delete;
    EditText name1, surname1, father1, nationalID1, birthDate1, gender1;
    StudentSave studentSave;
    List<StudentSave> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentSave = new StudentSave(this);
        list = studentSave.getAllStudent(SQLiteEdit.this);
        setContentView(R.layout.activity_sqlite_edit);

        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);

        name1 = (EditText) findViewById(R.id.name1);
        surname1 = (EditText) findViewById(R.id.surname1);
        father1 = (EditText) findViewById(R.id.father1);
        nationalID1 = (EditText) findViewById(R.id.nationalID1);
        birthDate1 = (EditText) findViewById(R.id.birthDate1);
        gender1 = (EditText) findViewById(R.id.gender1);

        name1.setText(SQLiteFetchN.SQLName);
        surname1.setText(SQLiteFetchN.SQLSurname);
        father1.setText(SQLiteFetchN.SQLfather);
        nationalID1.setText(SQLiteFetchN.SQLnationalID);
        birthDate1.setText(SQLiteFetchN.SQLbirthDate);
        gender1.setText(SQLiteFetchN.SQLgender);

        edit.setOnClickListener(e-> {
            Toast.makeText(getBaseContext(), "Student updated successfully in SQLite", Toast.LENGTH_SHORT).show();
            studentSave.updateStudent((new StudentSave(name1.getText().toString(), surname1.getText().toString(), father1.getText().toString(), Integer.parseInt(nationalID1.getText().toString()), birthDate1.getText().toString(), gender1.getText().toString(), SQLiteEdit.this)), SQLiteFetchN.SQLid);
            finish();
        });

        delete.setOnClickListener(e-> {
            Toast.makeText(getBaseContext(), "Student deleted successfully in SQLite", Toast.LENGTH_SHORT).show();
            studentSave.deleteStudent(list.get(SQLiteFetchN.SQLposition));
            finish();
        });
    }
}