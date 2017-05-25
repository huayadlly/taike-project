package com.mouse.folder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.mouse.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by huayadlly on 2017/5/25.
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageUtils {

    @JsonProperty(value = "total_count")
    private Integer totalCount;

    @JsonProperty(value = "total_page")
    private Integer totalPage;

    @JsonProperty(value = "page_size")
    private Integer pageSize;

    @JsonProperty(value = "start_page")
    private Integer startPage;

    @JsonProperty(value = "start_index")
    private Integer startIndex;

    @JsonProperty(value = "entity_list")
    private List<Book> entityList = Lists.newArrayList();

    public PageUtils() {
    }

//    public PageUtils(){
//
//    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<Book> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Book> entityList) {
        this.entityList = entityList;
    }
}
