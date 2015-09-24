package JPA;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by szalimben on 22/09/15.
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "venta.findAll", query = "select v from VentaEntity v"),
		@NamedQuery(name = "venta.totalRegisters", query = "select count(v.id) from VentaEntity v"),
		@NamedQuery(name = "venta.findById", query = "select v from VentaEntity v where v.id=:id")
})
@Table(name = "venta", schema = "public", catalog = "tienda")
public class VentaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venta")
	@SequenceGenerator(name = "seq_venta", sequenceName = "seq_name")
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@Expose
	private Long id;

	@Basic
	@Column(name = "fecha", nullable = false, insertable = true, updatable = true)
	@Expose
	private String fecha;

	@ManyToOne(optional = true)
	@JoinColumn(name = "factura_id")
	@Expose
	private FacturaEntity factura;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id")
	@Expose
	private ClienteEntity cliente;

	@OneToMany(mappedBy = "venta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Expose
	private List<VentaDetalleEntity> detalles;

	@Basic
	@Column(name = "monto", nullable = true, insertable = true, updatable = true)
	@Expose
	private Long monto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		VentaEntity that = (VentaEntity) o;

		if(id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if(fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) {
			return false;
		}
		if(monto != null ? !monto.equals(that.monto) : that.monto != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
		result = 31 * result + (monto != null ? monto.hashCode() : 0);
		return result;
	}

	public FacturaEntity getFactura() {
		return factura;
	}

	public void setFactura(FacturaEntity factura) {
		this.factura = factura;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public List<VentaDetalleEntity> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<VentaDetalleEntity> detalles) {
		this.detalles = detalles;
	}
}
