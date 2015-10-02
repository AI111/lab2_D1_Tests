package com.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by sasha on 02.10.15.
 */
public class StudentRepositoryImpl implements StudentRepository{
//    @Autowired
//    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    @Override
    public void createStudent(Student student) {
        String sql = "INSERT INTO student (first_name,last_name, birthday, groupe) VALUES (?, ?, ?, ?, ?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, student.getName(), student.getSurname(),student.getBirthday(),student.getGroupe());

    }

    @Override
    public Student getStudent() {
        return null;
    }

    @Override
    public void removeStudent(Student student) {

    }

    @Override
    public void editStudent(Student student) {

    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "select * from student";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return  jdbcTemplate.query(sql, new StudentRowMapper());
    }
}
