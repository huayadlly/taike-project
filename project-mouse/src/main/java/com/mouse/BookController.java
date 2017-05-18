package com.mouse;

import com.google.common.collect.Maps;
import com.mouse.jpa.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by huayadlly on 2017/5/18.
 */
@RestController
public class BookController {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @RequestMapping(value = "/get/count")
    public Map<String, String> getCount() {
        Map<String, String> map = Maps.newHashMap();
        try {
            Long size = bookJpaRepository.countbyId(2);
            map.put("COUNT", String.valueOf(size));
            map.put("SUCCESS", "Y");
        } catch (Exception e) {
            map.put("SUCCESS", "N");
            e.printStackTrace();
        }
        return map;
    }
}
