package com.mouse.utils;

import com.google.common.collect.Lists;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by huayadlly on 2017/5/22.
 */
public class FileTraversalUtils {


    //遍历文件夹:递归方式遍历文件夹
    public static List<File> traversalDirectory(Path path) {
        List<File> list = Lists.newArrayList();

        File file = path.toFile();
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                traversalDirectory(f.toPath());
            } else {
                if (isCondition()) {
                    //TODO 对满足条件的文件进行操作
                    list.add(f);
                }
            }
        }
        return list;
    }

    //判断文件是否满足条件
    public static boolean isCondition() {
        return true;
    }
}
