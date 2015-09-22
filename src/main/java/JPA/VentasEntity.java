package JPA;

import javax.persistence.*;

/**
 * Created by alex on 31/08/15.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "ventas.findAll", query = "select v from VentasEntity v"),
        @NamedQuery(name = "ventas.totalRegisters", query = "select count(v.id) from VentasEntity v"),
        @NamedQuery(name = "ventas.findById", query = "select v from VentasEntity v where v.id=:id")
})

@Table(name = "ventas", schema = "public", catalog = "ventas")
public class VentasEntity {
    private int id;
    private String numero;
    private String montoTotal;
    private String nombreCliente;
    private String rucCliente;
    private String fecha;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "numero", nullable = true, insertable = true, updatable = true)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Basic
    @Column(name = "monto_total", nullable = true, insertable = true, updatable = true)
    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

    @Basic
    @Column(name = "nombre_cliente", nullable = true, insertable = true, updatable = true, length = 50)
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Basic
    @Column(name = "ruc_cliente", nullable = true, insertable = true, updatable = true)
    public String getRucCliente() {
        return rucCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    @Basic
    @Column(name = "fecha", nullable = true, insertable = true, updatable = true)
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentasEntity that = (VentasEntity) o;

        if (id != that.id) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (montoTotal != null ? !montoTotal.equals(that.montoTotal) : that.montoTotal != null) return false;
        if (nombreCliente != null ? !nombreCliente.equals(that.nombreCliente) : that.nombreCliente != null)
            return false;
        if (numero != null ? !numero.equals(that.numero) : that.numero != null) return false;
        if (rucCliente != null ? !rucCliente.equals(that.rucCliente) : that.rucCliente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (montoTotal != null ? montoTotal.hashCode() : 0);
        result = 31 * result + (nombreCliente != null ? nombreCliente.hashCode() : 0);
        result = 31 * result + (rucCliente != null ? rucCliente.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
