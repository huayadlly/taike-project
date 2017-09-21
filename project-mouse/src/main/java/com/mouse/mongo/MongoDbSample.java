package com.mouse.mongo;


import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

/**
 * Created by huayadlly on 2017/6/24.
 */
public class MongoDbSample {

    private static final String HOST = "127.0.0.1";
    private static final Integer PORT = 27017;

    public static void testMongoDB(String adminName, String databaseName, String password) {
        try {
            ServerAddress serverAddress = new ServerAddress(HOST, PORT);
            List<ServerAddress> serverAddressList = Lists.newArrayList(serverAddress);

            MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(
                    adminName, databaseName, password.toCharArray());
            List<MongoCredential> credentialList = Lists.newArrayList(mongoCredential);

            MongoClient client = new MongoClient(serverAddressList, credentialList);
            System.out.println("获得数据库连接成功！");

            //选择数据库和集文档集合
            MongoDatabase book = client.getDatabase("book");
            MongoCollection<Document> bookSection = book.getCollection("book_section");
            System.out.println("选择数据库文档成功！");

            Document document = new Document();
            document.append("name", "java insertMany test");
            document.append("database", "use mongodb database");

            bookSection.insertOne(document);
            System.out.println("文档插入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testMongoDB("bookAdmin", "book", "123456");
    }


}
