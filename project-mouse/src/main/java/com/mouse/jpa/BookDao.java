package com.mouse.jpa;

import com.mouse.entity.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by huayadlly on 2017/5/31.
 */
@Named
public class BookDao {

    @Named("dataSource")
    @Inject
    private DataSource dataSource;

    private QueryRunner queryRunner = new QueryRunner();

    //查询数据库中总记录数
    public Integer queryTotalCount() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT COUNT(1) FROM book_bak";
            Long query = queryRunner.query(conn, sql, new ScalarHandler<Long>(1));
            return new Long(query).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询数据集合
    public List<Book> queryBookList(Integer startIndex, Integer pageSize) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT bb.id,bb.type,bb.book_name AS bookName,bb.project_name AS projectName,bb.book_info_id AS bookInfoId,bb.book_id AS bookId FROM book_bak bb LIMIT ?,?";
            return queryRunner.query(conn, sql, new BeanListHandler<>(Book.class), startIndex, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
