package JPA;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

/**
 * Created by szalimben on 22/09/15.
 */
@Entity
@Table(name = "compra_detalle", schema = "public", catalog = "tienda")
public class CompraDetalleEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@Expose
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_compra_detalle")
	@SequenceGenerator(name = "seq_compra_detalle", sequenceName = "seq_compra_detalle")
	private Long id;

	@Basic
	@Column(name = "cantidad", nullable = true, insertable = true, updatable = true)
	@Expose
	private Long cantidad;

	@ManyToOne(optional=false)
	@JoinColumn(name = "compra_id")
	@Expose
	private CompraEntity compra;

	@ManyToOne(optional=false)
	@JoinColumn(name = "producto_id")
	@Expose
	private ProductoEntity producto;

	public CompraDetalleEntity() {
		// Constructor por defecto
	}

	public CompraDetalleEntity (long cantidad, CompraEntity compra, ProductoEntity producto) {
		this.cantidad = cantidad;
		this.compra = compra;
		this.producto = producto;
	}

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

		CompraDetalleEntity that = (CompraDetalleEntity) o;

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

	public CompraEntity getCompra() {
		return compra;
	}

	public void setCompra(CompraEntity compra) {
		this.compra = compra;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
}
