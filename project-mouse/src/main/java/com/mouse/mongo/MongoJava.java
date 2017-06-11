package com.mouse.mongo;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j;
import org.bson.Document;

import java.util.List;

/**
 * Created by huayadlly on 2017/6/11.
 */
@Log4j
public class MongoJava {

    private static final String HOST = "localhost";
    private static final Integer PORT = 27027;
    private static MongoClient client;

    public MongoJava() {
        try {
            client = new MongoClient(HOST, PORT);
            log.info("Connection to MongoDB Database Success!");
        } catch (Exception e) {
            log.error("Fail to Connection MongoDB!");
            e.printStackTrace();
        }
    }

    public void mongoJdbc() {
        //连接数据库
        MongoDatabase database = client.getDatabase("myDb");
        database.createCollection("myCollection");
        log.info("Create Collection Successful !");

//        MongoCollection<Document> myCollection = database.getCollection("myCollection");
//
//        //向mongodb数据库中插入文档
//        Document document = new Document("title", "mongo_document");
//        document.append("description", "MongoDB Document");
//        document.append("likes", 100);
//        document.append("by", "Guava");
//        document.append("project", "Java");
//
//        List<Document> list = Lists.newArrayList(document);
//
//        myCollection.insertMany(list);
//        log.info("数据插入成功！");
    }

    public static void main(String[] args) {
        new MongoJava().mongoJdbc();
    }
}
