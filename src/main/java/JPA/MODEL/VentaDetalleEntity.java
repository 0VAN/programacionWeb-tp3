package JPA.MODEL;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

/**
 * Created by szalimben on 22/09/15.
 */
@Entity
@Table(name = "venta_detalle", schema = "public", catalog = "tienda")
public class VentaDetalleEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@Expose
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venta_detalle")
	@SequenceGenerator(name = "seq_venta_detalle", sequenceName = "seq_venta_detalle")
	private Long id;

	@Basic
	@Column(name = "cantidad", nullable = true, insertable = true, updatable = true)
	@Expose
	private Long cantidad;

	@ManyToOne(optional=false)
	@JoinColumn(name = "producto_id")
	@Expose
	private ProductoEntity producto;

	@ManyToOne(optional=false)
	@JoinColumn(name = "venta_id")
	@Expose
	private VentaEntity venta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		VentaDetalleEntity that = (VentaDetalleEntity) o;

		if(id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if(cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
		return result;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public VentaEntity getVenta() {
		return venta;
	}

	public void setVenta(VentaEntity venta) {
		this.venta = venta;
	}
}
