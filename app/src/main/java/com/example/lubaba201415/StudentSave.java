package com.example.lubaba201415;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class StudentSave {
    private SQLiteHelper SQLitedb;
    private int ID;
    private String name;
    private String surname;
    private String father;
    private int nationalID;
    private String birthDate;
    private String gender;

    public StudentSave(Context context){
        SQLitedb=new SQLiteHelper(context);
    }

    public String toString() {
        return "FavoriteAnnouncement [ID=" + ID + ", name=" + name + ", surname=" + surname + ", father=" + father + ", nationalID=" + nationalID + ", birthDate=" +birthDate +",gender=" + gender +"]";
    }

    public StudentSave(String name, String surname, String father, int nationalID, String birthDate, String gender, Context context) {
        this.name = name;
        this.surname = surname;
        this.father = father;
        this.nationalID = nationalID;
        this.birthDate = birthDate;
        this.gender = gender;
        SQLitedb=new SQLiteHelper(context);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFather() {
        return father;
    }

    public int getNationalID() {
        return nationalID;
    }

    public String getBirthDate() { return birthDate; }

    public String getGender() {
        return gender;
    }

    public void setID(int ID) { this.ID = ID; }

    public void setName(String name) { this.name = name; }

    public void setSurname(String surname) { this.surname = surname; }

    public void setFather(String father) { this.father = father; }

    public void setNationalID(int nationalID) { this.nationalID = nationalID; }

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public void setGender(String gender) { this.gender = gender; }

    private static final String TABLE_Student = "Student";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_FATHER = "father";
    private static final String KEY_NATIONALID = "nationalID";
    private static final String KEY_BIRTHDATE = "birthDate";
    private static final String KEY_GENDER = "gender";
    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_SURNAME,KEY_FATHER,KEY_NATIONALID,KEY_BIRTHDATE,KEY_GENDER};

    public void addStudent(StudentSave studentSave){
        SQLiteDatabase db = SQLitedb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, studentSave.getName());
        values.put(KEY_SURNAME, studentSave.getSurname());
        values.put(KEY_FATHER, studentSave.getFather());
        values.put(KEY_NATIONALID, studentSave.getNationalID());
        values.put(KEY_BIRTHDATE, studentSave.getBirthDate());
        values.put(KEY_GENDER, studentSave.getGender());

        db.insert(TABLE_Student, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        db.close();
    }

    public ArrayList<StudentSave> getAllStudent(Context context) {
        ArrayList<StudentSave> allStudent = new ArrayList<StudentSave>();

        String query = "SELECT  * FROM " + TABLE_Student;
        SQLiteDatabase db = SQLitedb.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        StudentSave student = null;
        if (cursor.moveToFirst()) {
            do {
                student = new StudentSave(context);
                student.setID(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setSurname(cursor.getString(2));
                student.setFather(cursor.getString(3));
                student.setNationalID(Integer.parseInt(cursor.getString(4)));
                student.setBirthDate(cursor.getString(5));
                student.setGender(cursor.getString(6));

                allStudent.add(student);
            } while (cursor.moveToNext());
        }
        return allStudent;
    }

    public void deleteStudent(StudentSave student) {
        SQLiteDatabase db = SQLitedb.getWritableDatabase();
        db.delete(TABLE_Student,
                KEY_ID+" = ?",
                new String[] { String.valueOf(student.getID()) });
        db.close();
    }

    public int updateStudent(StudentSave student, int studentId) {
        SQLiteDatabase db = SQLitedb.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", student.getName());
        values.put("surname", student.getSurname());
        values.put("father", student.getFather());
        values.put("nationalID", student.getNationalID());
        values.put("birthDate", student.getBirthDate());
        values.put("gender", student.getGender());

        int i = db.update(TABLE_Student, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(studentId) }); //selection args
        db.close();
        return i;
    }
}