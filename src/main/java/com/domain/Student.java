package com.domain;

import java.sql.Date;
import java.util.Objects;

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
    public Student( String name, String surname, Date birthday, String groupe) {
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
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        if (birthday != null ? !birthday.equals(student.birthday) : student.birthday != null) return false;
        return !(groupe != null ? !groupe.equals(student.groupe) : student.groupe != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (groupe != null ? groupe.hashCode() : 0);
        return result;
    }
}
