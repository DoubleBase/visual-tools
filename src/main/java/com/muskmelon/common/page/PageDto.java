package com.muskmelon.common.page;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-1 23:18
 * @since 1.0
 */
public class PageDto {

    private int limit;
    private int offset;
    private String order;
    private String search;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
