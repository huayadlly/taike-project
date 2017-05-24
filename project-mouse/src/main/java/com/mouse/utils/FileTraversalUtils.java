package com.mouse.utils;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
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

    //判断文件是否满足条件
    public static boolean isCondition(File file) {
        if (file.getName().startsWith(".")) {
            return false;
        }
        return true;
    }


    //Java8的方式遍历文件夹
    public static List<File> traversalDirectoryJava8(Path start) {
        List<File> list = Lists.newArrayList();
        try {
            Files.walkFileTree(start, EnumSet.allOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (fileFilter.accept(file.toFile())) {
                        //满足条件，将文件添加到集合中
                        list.add(file.toFile());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //定义一个过滤器接口，接受指定类型的文件
    public static FileFilter fileFilter = FileFilterUtils.and(
            FileFilterUtils.fileFileFilter(),
            FileFilterUtils.notFileFilter(
                    FileFilterUtils.prefixFileFilter("~$") //不要开头为“~$”的文件
            ),
            FileFilterUtils.suffixFileFilter(".xlsx") //只要结尾为“.xlsx”的文件
    );


    //神奇：获得Connection<File>文件集合
    public static void aaa(String path){
        File file = new File(path);

        Collection<File> result = FileUtils.listFiles(
                file,
                FileFilterUtils.notFileFilter(
                        FileFilterUtils.prefixFileFilter(".")
                ),
                FileFilterUtils.trueFileFilter()
        );

        Iterator<File> iterator = result.iterator();

    }

}
