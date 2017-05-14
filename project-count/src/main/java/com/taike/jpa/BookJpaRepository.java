package com.taike.jpa;

import com.taike.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huayadlly on 2017/5/14.
 */
@Repository
public interface BookJpaRepository extends CrudRepository<Book,String> {

    //条件查询，统计数据表中符合条件的记录数
    Integer countById(String id);

    @Query(value = "SELECT COUNT(1) FROM book_info",nativeQuery = true)
    Integer countThroghNativeSql();
}
