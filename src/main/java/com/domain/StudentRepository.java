package com.domain;

import java.util.List;

/**
 * Created by sasha on 02.10.15.
 */
public interface StudentRepository {
    void createStudent(Student student);
    Student getStudent();
    void removeStudent(Student student);
    void editStudent(Student student);
    List<Student> getAllStudents();

}
