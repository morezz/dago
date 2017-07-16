package dago.utils;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResults<T> implements Serializable {

    private static final long serialVersionUID = 8459184039167837494L;
    //上一页
    private int previousPage;

    public int getPreviousPage() {
        return previousPage;
    }

    // 当前页
    private int currentPage;

    public int getCurrentPage() {
        return currentPage;
    }

    // 下一页
    private int nextPage;

    public int getNextPage() {
        return nextPage;
    }

    // 每页数量
    private int pageSize;

    public int getPageSize() {
        return pageSize;
    }

    // 总条数
    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    // 总页数
    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    // 记录
    private List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void initPageResults(final int pageSize,
                                final int totalCount,
                                final int currentPage) {
        this.setPageSize(pageSize);
        this.setTotalCount(totalCount);
        this.setPageCount(createPageCount());


        this.setCurrentPage(currentPage);
        this.setNextPage(this.getCurrentPage() + 1);
        this.setPreviousPage(this.getCurrentPage() - 1);
    }

    public void setPreviousPage(int previousPage) {
        if (previousPage <= 0 || pageCount == 0) {
            this.previousPage = 1;
        } else {
            this.previousPage = previousPage;
        }
    }

    public void setNextPage(int nextPage) {
        if (nextPage <= 0 || pageCount == 0) {
            this.nextPage = 1;
        } else {
            if (nextPage > pageCount && pageCount > 0) {
                this.nextPage = pageCount;
            } else {
                this.nextPage = nextPage;
            }
        }
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage <= 0 || pageCount == 0) {
            this.currentPage = 1;
        } else {
            if (currentPage > pageCount && pageCount > 0) {
                this.currentPage = pageCount;
            } else {
                this.currentPage = currentPage;
            }
        }
    }

    //默认每页十条
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
    }

    public int createPageCount() {
        return totalCount % pageSize == 0 ? totalCount / pageSize
                : totalCount / pageSize + 1;
    }
}
