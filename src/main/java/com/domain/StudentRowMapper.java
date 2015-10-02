package com.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sasha on 02.10.15.
 */
public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5));
    }
}
