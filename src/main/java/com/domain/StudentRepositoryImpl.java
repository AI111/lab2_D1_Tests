package com.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by sasha on 02.10.15.
 */
@Repository
public class StudentRepositoryImpl implements StudentRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void createStudent(Student student) {
        String sql = "INSERT INTO student (first_name,last_name, birthday, groupe) VALUES (?, ?, ?, ?)";
       // JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, student.getName(), student.getSurname(),student.getBirthday(),student.getGroupe());
    }

    @Override
    public Student getStudent(int id) {
        String sql = "SELECT * FROM student WHERE id = "+id;
        return jdbcTemplate.queryForObject(sql,new StudentRowMapper());
    }

    @Override
    public void removeStudent(Student student) {
    String sql = "DELETE FROM student WHERE id="+student.getId();
        jdbcTemplate.update(sql);
    }

    @Override
    public void editStudent(Student student) {
        String sql = "UPDATE student SET first_name = ?,last_name = ?, birthday = ?, groupe = ? WHERE id = ?";
        jdbcTemplate.update(sql,student.getName(),student.getSurname(),student.getBirthday(),student.getGroupe(),student.getId());
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "select * from student";
      //  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return  jdbcTemplate.query(sql, new StudentRowMapper());
    }
}
