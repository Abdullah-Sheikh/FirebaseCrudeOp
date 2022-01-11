package com.example.firebasecrudeop.Model;

public class Student {

    String name, id, CNIC;
    int Age, CGPA;

    public Student() {
    }

    public Student(String name, String id, String CNIC, int age, int CGPA) {
        this.name = name;
        this.id = id;
        this.CNIC = CNIC;
        Age = age;
        this.CGPA = CGPA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getCGPA() {
        return CGPA;
    }

    public void setCGPA(int CGPA) {
        this.CGPA = CGPA;
    }
}