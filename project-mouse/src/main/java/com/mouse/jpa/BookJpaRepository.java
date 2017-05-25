package com.mouse.jpa;

import com.mouse.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huayadlly on 2017/5/18.
 */
@Repository
public interface BookJpaRepository extends JpaRepository<Book, Integer> {

    //统计表中数据
    long countBookByIdIs(Integer id);

    //统计表中全部的数据
    long count();

}
