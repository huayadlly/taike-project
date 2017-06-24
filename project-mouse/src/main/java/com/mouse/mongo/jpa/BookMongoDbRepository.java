package com.mouse.mongo.jpa;

import com.mouse.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huayadlly on 2017/6/24.
 */
public interface BookMongoDbRepository extends JpaRepository<Book, Integer> {

    Book findAllByBookId();
}
