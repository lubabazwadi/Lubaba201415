package com.example.lubaba201415;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;// Database Version
    private static final String DATABASE_NAME = "DataStore2";// Database Name

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_FATHER = "father";
    private static final String KEY_NATIONALID = "nationalID";
    private static final String KEY_BIRTHDATE = "birthDate";
    private static final String KEY_GENDER = "gender";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE Student ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "surname TEXT,"+
                "father TEXT, "+
                "nationalID INTEGER,"+
                "birthDate TEXT ,"+
                "gender TEXT )";
        db.execSQL(CREATE_STUDENT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        this.onCreate(db);
    }
}