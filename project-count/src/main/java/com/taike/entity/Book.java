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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "book_info_id")
    private String bookInfoId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "type")
    private String type;

    @Column(name = "book_id")
    private Long bookId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
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

    public String getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(String bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public Book() {
    }

    public Book(String bookInfoId, String bookName, String projectName, String type, Long bookId) {
        this.bookInfoId = bookInfoId;
        this.bookName = bookName;
        this.projectName = projectName;
        this.type = type;
        this.bookId = bookId;
    }
}
