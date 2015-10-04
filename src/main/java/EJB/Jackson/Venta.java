package EJB.Jackson;

import java.util.List;

/**
 * Created by alex on 03/10/15.
 */
public class Venta {
    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<VentaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<VentaDetalle> detalles) {
        this.detalles = detalles;
    }

    private Integer clienteId;
    private String fecha;
    private List<VentaDetalle> detalles;
}
