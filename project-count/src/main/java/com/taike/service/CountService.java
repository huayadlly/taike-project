package com.taike.service;

import com.taike.entity.Book;
import com.taike.jpa.BookJpaRepository;
import com.taike.jpa.CountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huayadlly on 2017/5/14.
 */
@Service
public class CountService {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Autowired
    private CountDao countDao;

    //统计记录数
    public Integer countSize() {
        //DBUtils方式查询数据库
        Integer count1 = countDao.count();

        //使用JPA原生的方法查询，条件查询
        Integer integer = bookJpaRepository.countById("1646a637-34ad-4d26-a36c-d7678bdd63d4");

        //使用JPA框架，但是使用的是原生SQL方式查询数据库
        Integer count = bookJpaRepository.countThroghNativeSql();
        return count;
    }

    //查询全部的数据
    public List<Book> findAll() {
        return countDao.findAllBook();
    }

    //将数据保存到数据库中
    public void saveBook(Book book){
        countDao.saveBook(book);
    }

}
