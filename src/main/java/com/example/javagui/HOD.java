package com.example.javagui;

public class HOD extends Employee{

    String name;
    String grade;
    public HOD(String name, String grade) {
        super(name, grade);
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "HOD " + name + " " + grade;
    }
}

