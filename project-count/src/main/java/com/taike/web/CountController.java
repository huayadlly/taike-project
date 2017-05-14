package com.taike.web;

import com.google.common.collect.Maps;
import com.taike.service.CountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
