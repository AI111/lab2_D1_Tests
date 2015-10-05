package com.application;

import com.domain.Student;

import java.util.List;

/**
 * Created by sasha on 02.10.15.
 */
public interface ApplicationService {
    int concatStudentName3();
    List<Student> getAllStudentsWithRepeatedNames();

}
