package EJB.Helper;

/**
 *
 * Contiene los valores para el paginado de las listas.
 * Incluye la cantidad total de registros, la cantidad total de paginas
 * y el tamano de cada pagina
 *
 * @author szalimben
 */
public class Meta {

    /* Cantidad  */
    Integer total;
    Integer total_pages;
    Integer page_size;

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

    public void calculateToTalPages() {

        int var = getTotal() / getPage_size();
        setTotal_pages(var);
    }


}
