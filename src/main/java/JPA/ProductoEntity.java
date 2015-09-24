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

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		ProductoEntity that = (ProductoEntity) o;

		if(id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if(stock != null ? !stock.equals(that.stock) : that.stock != null) {
			return false;
		}
		if(precio != null ? !precio.equals(that.precio) : that.precio != null) {
			return false;
		}
		if(descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (stock != null ? stock.hashCode() : 0);
		result = 31 * result + (precio != null ? precio.hashCode() : 0);
		result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
		return result;
	}

	public List<SolicitudCompraEntity> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<SolicitudCompraEntity> solicitudes) {
		this.solicitudes = solicitudes;
	}
}
