package JPA.MODEL;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by szalimben on 22/09/15.
 */
@Entity
@Table(name = "compra", schema = "public", catalog = "tienda")
public class CompraEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	private Long id;

	@Basic
	@Column(name = "fecha", nullable = false, insertable = true, updatable = true)
	private Timestamp fecha;

	@Basic
	@Column(name = "monto", nullable = true, insertable = true, updatable = true)
	private Long monto;

	@ManyToOne
	@JoinColumn(name = "proveedor_id")
	private ProveedorEntity proveedor;

	@OneToMany(mappedBy = "compra")
	private List<CompraDetalleEntity> detalles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
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

		CompraEntity that = (CompraEntity) o;

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

	public ProveedorEntity getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorEntity proveedor) {
		this.proveedor = proveedor;
	}

	public List<CompraDetalleEntity> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<CompraDetalleEntity> detalles) {
		this.detalles = detalles;
	}
}
