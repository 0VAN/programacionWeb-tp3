package JPA;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

/**
 * Created by szalimben on 22/09/15.
 */
@Entity
@Table(name = "producto", schema = "public", catalog = "tienda")
public class ProductoEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@Expose
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
	@SequenceGenerator(name = "seq_producto", sequenceName = "seq_producto")
	private Long id;

	@Basic
	@Column(name = "stock", nullable = true, insertable = true, updatable = true)
	@Expose
	private Long stock;

	@Basic
	@Column(name = "precio", nullable = true, insertable = true, updatable = true)
	@Expose
	private Long precio;

	@Basic
	@Column(name = "descripcion", nullable = true, insertable = true, updatable = true, length = 100)
	@Expose
	private String descripcion;

	public Long getId() {
		return id;
	}

	@OneToMany(mappedBy = "producto")
	private List<SolicitudCompraEntity> solicitudes;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
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
}
