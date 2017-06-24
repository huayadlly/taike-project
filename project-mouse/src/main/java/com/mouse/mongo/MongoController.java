package com.mouse.mongo;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by huayadlly on 2017/6/13.
 */
@Controller
public class MongoController {

    //向mongodb中添加数据
    @RequestMapping(value = "/mongodb/add/book", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addBook() {
        Map<String, String> msg = Maps.newHashMap();
        try {


            msg.put("SUCCESS", "YES");
        } catch (Exception e) {
            msg.put("SUCCESS", "NO");
            e.printStackTrace();
        }
        return msg;
    }


}
