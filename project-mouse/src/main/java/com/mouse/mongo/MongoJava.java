package com.mouse.mongo;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.List;

/**
 * Created by huayadlly on 2017/6/11.
 */
@Slf4j
public class MongoJava {

    public void mongoJdbc() {
        try {
            //连接数据库
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mydb = mongoClient.getDatabase("mydb");
            log.info("Connection to MongoDB Successful");

            //创建集合
//            mydb.createCollection("test");
//            log.info("Create Collection Successful");

            //选择集合
            MongoCollection<Document> collection = mydb.getCollection("test");
            log.info("选择集合 成功");

            //向集合中添加文档
            Document document = new Document();
            document.append("title", "MongoDB");
            document.append("description", "MongoDB is no SQL database");
            document.append("likes", 87);
            document.append("tags", Lists.newArrayList("Java", "PHP", "C#"));
            List<Document> list = Lists.newArrayList(document);

            //向集合中插入文档
            collection.insertMany(list);
            log.info("向集合中插入文档 成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDocument() {
        try {
            //连接数据库
            MongoClient client = new MongoClient("localhost", 27017);
            //选择数据库
            MongoDatabase mydb = client.getDatabase("mydb");
            //选择集合
            MongoCollection<Document> test = mydb.getCollection("test");
            log.info("选择集合 成功");

            //更新文档  更新文档的likes值
            test.updateMany(Filters.eq("likes", 120), new Document("$set", new Document("likes", 66)));
            log.info("更新集合 成功");

            //查看检索结果
            FindIterable<Document> documents = test.find();
            for (Document document : documents) {
                System.out.println("文档：" + document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MongoJava mongoJava = new MongoJava();
        //添加文档
//        mongoJava.mongoJdbc();

        //更新文档
        mongoJava.updateDocument();
    }
}
