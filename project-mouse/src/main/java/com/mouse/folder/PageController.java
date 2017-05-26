package com.mouse.folder;

import com.google.common.collect.Maps;
import com.mouse.entity.Book;
import com.mouse.jpa.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by huayadlly on 2017/5/25.
 */
@RestController
public class PageController {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    private Integer startPage = 1;
    private Integer pageSize = 10;

    //使用jpa的方式查询数据库
    @RequestMapping(value = "/page/query/jpa", method = RequestMethod.GET)
    public Map<String, String> pageQueryJpa() {
        Map<String, String> msg = Maps.newHashMap();
        try {

            Pageable pageable = new PageRequest(startPage,pageSize, Sort.Direction.DESC,"id");
            Page<Book> pageBook = bookJpaRepository.findAll(pageable);

            PageUtils pageUtils = new PageUtils(pageBook);

            msg.put("SUCCESS","YES");
        } catch (Exception e) {
            msg.put("SUCCESS","NO");
            msg.put("MSG",e.getMessage());
        }
        return msg;
    }
}
