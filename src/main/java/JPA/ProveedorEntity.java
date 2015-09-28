package JPA;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "proveedor", schema = "public", catalog = "tienda")
public class ProveedorEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@Expose
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_proveedor")
	@SequenceGenerator(name = "seq_proveedor", sequenceName = "seq_proveedor")
	private Long id;

	@Basic
	@Column(name = "descripcion", nullable = true, insertable = true, updatable = true, length = 100)
	@Expose
	private String descripcion;


	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	private List<ProductoEntity> productos;


	public ProveedorEntity() {
		// Constructor por defecto
	}

	public ProveedorEntity(String descripcion) {
		this.descripcion = descripcion;
	}


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

}
