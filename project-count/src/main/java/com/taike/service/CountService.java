package com.taike.service;

import com.taike.jpa.CountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huayadlly on 2017/5/14.
 */
@Service
public class CountService {

//    @Autowired
//    private BookJpaRepository bookJpaRepository;

    @Autowired
    private CountDao countDao;

    public Integer countSize() {
//        Integer count = bookJpaRepository.countById();
        return countDao.count();
    }
}
