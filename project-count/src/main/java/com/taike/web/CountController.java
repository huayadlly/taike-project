package com.taike.web;

import com.google.common.collect.Maps;
import com.taike.entity.Book;
import com.taike.service.CountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by huayadlly on 2017/5/14.
 */
@RestController
public class CountController {

    private static final Logger logger = LoggerFactory.getLogger(CountController.class);

    @Autowired
    private CountService countService;

    //查询数据库中记录数的借口
    @RequestMapping(value = "/count/size", method = RequestMethod.GET)
    public Map<String, String> countNumber() {
        Map<String, String> map = Maps.newHashMap();
        try {
            Integer count = countService.countSize();
            map.put("数据表中的记录数为:", String.valueOf(count));
            map.put("SUCCESS", "Y");
            return map;
        } catch (Exception e) {
            logger.error("查询出错！[{}]", e.getMessage());
            map.put("message", e.getMessage());
        }
        map.put("SUCCESS", "N");
        return map;
    }

    //程序方式备份数据表数据
    @RequestMapping(value = "/table/bak/data", method = RequestMethod.POST)
    public Map<String, String> tableBak() {
        Map<String, String> map = Maps.newHashMap();
        try {
            //查询得到全部的数据
            List<Book> list = countService.findAll();
            Integer size = list.size();
            Integer sizeFrozen = size;
            logger.info("开始备份：[{}]", sizeFrozen);

            //遍历集合--list.forEach(book -> {});
            for (Book book : list) {
                //降数据保存到数据库中
                countService.saveBook(book);
                size--;
                logger.info("剩余：[{}/{}]", size, sizeFrozen);
            }
            map.put("SUCCESS", "Y");
            return map;
        } catch (Exception e) {
            logger.error("查询出错！[{}]", e.getMessage());
            map.put("message", e.getMessage());
        }
        map.put("SUCCESS", "N");
        return map;
    }
}
