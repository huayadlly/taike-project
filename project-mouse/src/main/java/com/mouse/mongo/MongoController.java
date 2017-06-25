package com.mouse.mongo;

import com.google.common.collect.Maps;
import com.mouse.entity.Book;
import com.mouse.folder.PageUtils;
import com.mouse.jpa.BookJpaRepository;
import com.mouse.mongo.jpa.BookMongoDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by huayadlly on 2017/6/13.
 */
@Controller
public class MongoController {

    @Autowired
    private BookJpaRepository bookJpaRepository;
    @Autowired
    private BookMongoDbRepository bookMongoDbRepository;

    //向mongodb中添加数据
    @RequestMapping(value = "/mongodb/add/book", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addBook() {
        Map<String, String> msg = Maps.newHashMap();
        try {

            //分页查询
            Pageable pageable = new PageRequest(2,10, Sort.Direction.ASC);
            Page<Book> bookByPage = bookJpaRepository.findAll(pageable);
            List<Book> bookList = bookByPage.getContent();

            //向mongo数据库中存
            bookMongoDbRepository.save(bookList);

            msg.put("SUCCESS", "YES");
        } catch (Exception e) {
            msg.put("SUCCESS", "NO");
            e.printStackTrace();
        }
        return msg;
    }


}
