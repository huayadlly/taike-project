package com.mouse.replace;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huayadlly on 2017/5/16.
 */
public class ReplaceJsonFiled {

    private static final Logger logger = LoggerFactory.getLogger(ReplaceJsonFiled.class);

    public static void getFile(String filePath, String savePath) {
        File root = new File(filePath);
        File[] files = root.listFiles();

        ObjectMapper objectMapper = new ObjectMapper();

        for (File file : files) {
            if (!file.isDirectory()) {
                try {
                    //将JSON文件读到内存中
                    FileInputStream fis = new FileInputStream(file);
                    try (InputStreamReader in = new InputStreamReader(fis, "UTF-8")) {
                        Map<String, Object> map = objectMapper.readValue(in, Map.class);

                        if (map.get("listening") != null && !"".equals(String.valueOf(map.get("listening")))) {
                            map.put("listening", new HashMap<>());

                            //将map保存到文件中
                            Path path = Paths.get(savePath, String.valueOf(map.get("id")) + ".json");
                            objectMapper.writeValue(path.toFile(), map);
                        } else {
                            logger.error("课[{}]中没有listening字段!", map.get("name"));
                        }


                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        String filePath = "";
        String savePath = "";
        getFile(filePath, savePath);
    }

}
