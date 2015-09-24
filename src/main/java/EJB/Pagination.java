package EJB;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

/**
 * Created by alex on 22/09/15.
 */
@ApplicationScoped
public class Pagination implements Serializable {
    private int total;
    private int total_pages;
    private int page_size;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

}
