package com.mouse.mongo.jpa;

import com.mouse.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huayadlly on 2017/6/24.
 */
@Repository
public interface BookMongoDbRepository extends JpaRepository<Book, Integer> {

}
