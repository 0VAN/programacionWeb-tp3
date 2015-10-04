package EJB.Jackson;

/**
 * Created by alex on 04/10/15.
 */
public class CompraDetalle {
    private Integer productoId;

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    private Integer cantidad;
}
