package little.princess.sys.manager.web.page;

/**
 * @author: murasakiseifu
 * @date: 2018/12/21 2:17 PM}
 * @version: V1.0
 * @review: murasakiseifu/2018/12/21 2:17 PM}
 */
public class PageVo {

    private int pageNo = 1; // 当前页

    private int pageSize = 10; // 一页内记录数

    public PageVo(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
