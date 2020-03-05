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

    private Long total;

    public PageInfo(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
