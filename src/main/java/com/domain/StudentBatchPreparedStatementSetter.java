package com.domain;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by sasha on 02.10.15.
 */
public class StudentBatchPreparedStatementSetter implements BatchPreparedStatementSetter {
    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {

    }

    @Override
    public int getBatchSize() {
        return 0;
    }
}
