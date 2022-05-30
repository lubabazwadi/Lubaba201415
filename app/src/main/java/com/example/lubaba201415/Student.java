package com.example.lubaba201415;

public class Student {
    private int ID;
    private String name;
    private String surname;
    private String father;
    private int nationalID;
    private String birthDate;
    private String gender;
    //ID, Name, Surname, Father’s name, National ID, date of birth, Gender.
    public Student() { }

    public Student(int ID, String name, String surname, String father, int nationalID, String birthDate, String gender) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.father = father;
        this.nationalID = nationalID;
        this.birthDate = birthDate;
        this.gender = gender;
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

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }
}