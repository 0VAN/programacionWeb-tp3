package JPA;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;


/**
 * Clase que representa un Producto
 */
@Entity
@Table(name = "producto", schema = "public", catalog = "tienda")
public class ProductoEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@Expose
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
	@SequenceGenerator(name = "seq_producto", sequenceName = "seq_producto")
	private long id;

	@Basic
	@Column(name = "stock", nullable = true, insertable = true, updatable = true)
	@Expose
	private long stock;

	@Basic
	@Column(name = "precio", nullable = true, insertable = true, updatable = true)
	@Expose
	private long precio;

	@Basic
	@Column(name = "descripcion", nullable = true, insertable = true, updatable = true, length = 100)
	@Expose
	private String descripcion;

	@ManyToOne(optional=false)
	@JoinColumn(name = "proveedor_id")
	private ProveedorEntity proveedor;

	public ProductoEntity() {
		// Constructor por defecto
	}

	public ProductoEntity(long stock, String descripcion,long precio ) {
		this.stock = stock;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	@OneToMany(mappedBy = "producto")
	private List<SolicitudCompraEntity> solicitudes;

	public void setId(Long id) {
		this.id = id;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public long getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<SolicitudCompraEntity> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<SolicitudCompraEntity> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public ProveedorEntity getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorEntity proveedor) {
		this.proveedor = proveedor;
	}
}
