package com.application;

import com.domain.Student;

import java.util.List;

/**
 * Created by sasha on 4/28/15.
 */
public interface ApplicationService {
    int concatStudentName3();
    List<Student> getAllStudentsWithRepeatedNames();

}
