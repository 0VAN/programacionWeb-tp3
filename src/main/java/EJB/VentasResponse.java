package EJB;

import java.util.List;

/**
 * Created by alex on 24/09/15.
 */
public class VentasResponse {

    public Object getVentas() {
        return ventas;
    }

    public void setVentas(Object ventas) {
        this.ventas = ventas;
    }

    public Pagination getMeta() {
        return meta;
    }

    public void setMeta(Pagination meta) {
        this.meta = meta;
    }

    private Object ventas;
    private Pagination meta;

    public VentasResponse(Object lisaDeVentas,Pagination paginationInfo){
        setVentas(lisaDeVentas);
        setMeta(paginationInfo);
    }


}
