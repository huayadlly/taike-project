package com.mouse.folder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.mouse.entity.Book;
import com.mouse.jpa.BookDao;
import com.mouse.jpa.BookJpaRepository;
import org.apache.commons.io.FileUtils;
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
import java.util.List;
import java.util.Map;

/**
 * Created by huayadlly on 2017/5/25.
 */
@RestController
public class PageController {

    @Autowired
    private BookJpaRepository bookJpaRepository;
    @Autowired
    private BookDao bookdao;

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

    //使用DBUtils方式完成分页查询
    @RequestMapping(value = "/page/query/db", method = RequestMethod.GET)
    public Map<String, String> pageQueryDbutils() {
        Map<String, String> msg = Maps.newHashMap();
        try {
            //查询数据库中总记录数
            Integer totalCount = bookdao.queryTotalCount();
            int startIndex = (startPage - 1) * pageSize;
            //查询数据集合
            List<Book> bookList = bookdao.queryBookList(startIndex, pageSize);
            PageUtils pageUtils = new PageUtils(startPage, pageSize, startIndex, totalCount, bookList);

            String pageJson = mapper.writeValueAsString(pageUtils);
            //将生成的分页对象保存到本地
            Path savePath = Paths.get("D:\\test", "page_json_DBUtils.json");
            FileUtils.writeStringToFile(savePath.toFile(), pageJson);

            msg.put("SUCCESS", "YES");
        } catch (Exception e) {
            msg.put("SUCCESS", "NO");
            e.printStackTrace();
        }
        return msg;
    }

}
