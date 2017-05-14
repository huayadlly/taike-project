package com.taike.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Created by huayadlly on 2017/5/14.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "book_Info")
public class Book {

    @Id
    @GeneratedValue
    private String id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "type")
    private String type;

    @Column(name = "book_id")
    private String bookId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Book() {
    }

    public Book(String bookName, String projectName, String type, String bookId) {
        this.bookName = bookName;
        this.projectName = projectName;
        this.type = type;
        this.bookId = bookId;
    }
}
