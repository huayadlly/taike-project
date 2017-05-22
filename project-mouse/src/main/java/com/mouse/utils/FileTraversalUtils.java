package com.mouse.utils;

import com.google.common.collect.Lists;

import java.io.File;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
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
                if (isCondition(f)) {
                    //TODO 对满足条件的文件进行操作
                    list.add(f);
                }
            }
        }
        return list;
    }

    //Java8的方式遍历文件夹
    public static List<File> traversalDirectoryJava8(Path start) {
        List<File> list = Lists.newArrayList();
        try {
            Files.walkFileTree(start, EnumSet.allOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //判断文件是否满足条件
    public static boolean isCondition(File file) {
        if (file.getName().startsWith(".")) {
            return false;
        }
        return true;
    }
}
