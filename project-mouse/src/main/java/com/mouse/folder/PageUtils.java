package com.mouse.folder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.mouse.entity.Book;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by huayadlly on 2017/5/25.
 */
@Data
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageUtils {

    @JsonProperty(value = "total_count")
    private Integer totalCount;

    @JsonProperty(value = "total_page")
    private Integer totalPage;

    @JsonProperty(value = "page_size")
    private Integer pageSize;

    @JsonProperty(value = "current_page")
    private Integer currentPage;

    @JsonProperty(value = "start_index")
    private Integer startIndex;

    @JsonProperty(value = "entity_list")
    private List<Book> entityList = Lists.newArrayList();

    public PageUtils() {
    }

    public PageUtils(Page<Book> pageBook) {
        this.totalCount = pageBook.getNumberOfElements();
        this.totalPage = pageBook.getTotalPages();
        this.pageSize = pageBook.getSize();
        this.currentPage = pageBook.getNumber();
        this.entityList = pageBook.getContent();
        this.startIndex = (totalCount % pageSize == 0) ? totalCount / pageSize : (totalCount / pageSize) + 1;
    }

}
