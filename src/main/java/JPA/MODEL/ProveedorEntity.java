package JPA.MODEL;

import javax.persistence.*;
import java.util.List;

/**
 * Created by szalimben on 22/09/15.
 */
@Entity
@Table(name = "proveedor", schema = "public", catalog = "tienda")
public class ProveedorEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	private Long id;

	@Basic
	@Column(name = "descripcion", nullable = true, insertable = true, updatable = true, length = 100)
	private String descripcion;

	@OneToMany(mappedBy = "proveedor")
	private List<CompraEntity> compras;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

		ProveedorEntity that = (ProveedorEntity) o;

		if(id != null ? !id.equals(that.id) : that.id != null) {
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
		result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
		return result;
	}

	public List<CompraEntity> getCompras() {
		return compras;
	}

	public void setCompras(List<CompraEntity> compras) {
		this.compras = compras;
	}
}
