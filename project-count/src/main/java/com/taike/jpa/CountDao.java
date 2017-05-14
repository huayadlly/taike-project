package com.taike.jpa;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by huayadlly on 2017/5/14.
 */
@Component
public class CountDao {

    @Autowired
    DataSource dataSource;

    QueryRunner query = new QueryRunner();

    public Integer count(){
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT COUNT(1) FROM book_info";
            Object query = this.query.query(conn, sql, new ScalarHandler<>());
            return Integer.parseInt(String.valueOf(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
