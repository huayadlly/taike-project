package com.taike.jpa;

import com.taike.entity.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by huayadlly on 2017/5/14.
 */
@Component
public class CountDao {

    @Autowired
    DataSource dataSource;

    QueryRunner query = new QueryRunner();

    /*
       在使用DBUtils的时候，使用new ScalarHandler<>()处理结果集的时候，
       接收的类型可以是Integer/Object，但是不能是Integer类型，
       在使用Integer类型的时候，会报错:[java.lang.Long cannot be cast to java.lang.Integer]
     */
    public Integer count() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT COUNT(1) FROM book_info";
            Long count = query.query(conn, sql, new ScalarHandler<Long>());
            return Integer.valueOf(String.valueOf(count));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询全部的Book
    public List<Book> findAllBook() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,book_name AS bookName,project_name AS projectName,type,book_id AS bookId FROM book_info";
            return this.query.query(conn, sql, new BeanListHandler<>(Book.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //保存到数据库中
    public void saveBook(Book book) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO book_bak (id,book_name,project_name,type,book_id ) VALUES(?,?,?,?,?)";
            query.insert(conn, sql, new ScalarHandler<>(),
                    book.getId(), book.getBookName(), book.getProjectName(), book.getType(), book.getBookId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}