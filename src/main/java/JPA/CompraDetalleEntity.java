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
