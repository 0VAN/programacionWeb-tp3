package EJB;

import java.util.List;

/**
 * Created by alex on 22/09/15.
 */
public class Ventas {
    public List getVentas() {
        return ventas;
    }

    public void setVentas(List ventas) {
        this.ventas = ventas;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    private List ventas;
    private Meta meta;
}
