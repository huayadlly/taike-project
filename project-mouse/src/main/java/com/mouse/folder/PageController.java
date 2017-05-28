package com.mouse.folder;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by huayadlly on 2017/5/25.
 */
@RestController
public class PageController {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    private Integer startPage = 5;
    private Integer pageSize = 10;

    private static ObjectMapper mapper = new ObjectMapper();

    //使用jpa的方式查询数据库
    @RequestMapping(value = "/page/query/jpa", method = RequestMethod.GET)
    public Map<String, String> pageQueryJpa() {
        Map<String, String> msg = Maps.newHashMap();
        try {
            //jpa分页查询工具
            Pageable pageable = new PageRequest(startPage, pageSize, Sort.Direction.DESC, "id");
            Page<Book> pageBook = bookJpaRepository.findAll(pageable);

            //生成分页
            PageUtils pageUtils = new PageUtils(pageBook);

            //将生成的分页对象保存
            Path savePath = Paths.get("D:\\test", "page_json.json");
            mapper.writeValue(savePath.toFile(), pageUtils);

            msg.put("SUCCESS", "YES");
        } catch (Exception e) {
            msg.put("SUCCESS", "NO");
            msg.put("MSG", e.getMessage());
        }
        return msg;
    }
}
