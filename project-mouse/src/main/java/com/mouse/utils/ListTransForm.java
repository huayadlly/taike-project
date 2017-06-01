package com.mouse.utils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by huayadlly on 2017/5/22.
 */
public class ListTransForm {

    //转变list集合中的类型:将集合中的string类型转化成path类型
    public static List<Path> transLists(List<String> fileList) {
        List<Path> pathList = Lists.transform(fileList, input -> Paths.get(input));
        return pathList;
    }

    
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("D:\\test\\test_1.txt", "D:\\test\\test_2.txt");
        List<Path> paths = transLists(list);
    }

}
