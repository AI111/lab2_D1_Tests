package com.application;

import com.domain.Student;
import com.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * Created by sasha on 02.10.15.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    StudentRepository repository;

    @Transactional
    public int concatStudentName3() {
        int count = 0;
        List<Student> list = repository.getAllStudents();
        for (Student student : list) {
            if (student.getName().startsWith("E")) {
                student.setName(student.getName() + "_3");
                repository.editStudent(student);
                count++;
            }
        }
        return count;
    }

    @Transactional
    public List<Student> getAllStudentsWithRepeatedNames() {
        List<Student> answer = new LinkedList<>();
        List<Student> list = repository.getAllStudents();
        Map<String, LinkedList<Student>> studentMap = new LinkedHashMap<>();
        for (Student student : list) {
            if (studentMap.containsKey(student.getName())) {
                studentMap.get(student.getName()).add(student);
            } else {
                studentMap.put(student.getName(), new LinkedList<>(Arrays.asList(new Student[]{student})));
            }
        }
        studentMap.forEach(new BiConsumer<String, LinkedList<Student>>() {
            @Override
            public void accept(String s, LinkedList<Student> students) {
                if (students.size() > 1) answer.addAll(students);
            }
        });
        return answer;
    }
}
