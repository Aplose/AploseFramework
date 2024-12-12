package fr.aplose.aploseframework.model.dolibarr;

public class DolibarrPaginatedResponse<T> {
    private T[] data;
    private PaginationInfo pagination;

    public static class PaginationInfo {
        private int total;
        private int page;
        private int page_count;
        private int limit;

        // Getters et Setters
        public int getTotal() { return total; }
        public void setTotal(int total) { this.total = total; }
        public int getPage() { return page; }
        public void setPage(int page) { this.page = page; }
        public int getPage_count() { return page_count; }
        public void setPage_count(int page_count) { this.page_count = page_count; }
        public int getLimit() { return limit; }
        public void setLimit(int limit) { this.limit = limit; }
    }

    // Getters et Setters
    public T[] getData() { return data; }
    public void setData(T[] data) { this.data = data; }
    public PaginationInfo getPagination() { return pagination; }
    public void setPagination(PaginationInfo pagination) { this.pagination = pagination; }
}
