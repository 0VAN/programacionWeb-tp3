package JPA.MODEL;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by szalimben on 22/09/15.
 */
@Entity
@Table(name = "solicitud_compra", schema = "public", catalog = "tienda")
public class SolicitudCompraEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@GeneratedValue(generator = "SEQ_SOLICITUD_COMPRA", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_SOLICITUD_COMPRA", sequenceName = "seq_solicitud_compra")
	private Long id;

	@Basic
	@Column(name = "fecha", nullable = false, insertable = true, updatable = true)
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private ProductoEntity producto;

	@Column(name = "atendido", nullable = false, insertable = true, updatable = true)
	private boolean atendido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "fecha", nullable = false, insertable = true, updatable = true)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		SolicitudCompraEntity entity = (SolicitudCompraEntity) o;

		if(id != null ? !id.equals(entity.id) : entity.id != null) {
			return false;
		}
		if(fecha != null ? !fecha.equals(entity.fecha) : entity.fecha != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
		return result;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}

	public boolean isAtendido() {
		return atendido;
	}
}