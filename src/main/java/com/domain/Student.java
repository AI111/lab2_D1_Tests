package com.domain;

import java.sql.Date;

/**
 * Created by sasha on 02.10.15.
 */
public class Student {
    private int id;
    private String name;
    private String surname;
    private Date birthday;
    private String groupe;

    public Student() {
    }

    public Student(int id, String name, String surname, Date birthday, String groupe) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.groupe = groupe;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", groupe='" + groupe + '\'' +
                '}';
    }
}
