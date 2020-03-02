package com.muskmelon.common.page;

import java.util.List;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-1 23:23
 * @since 1.0
 */
public class PageInfo<T> {

    private List<T> rows;

    private Integer total;

    public PageInfo(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
