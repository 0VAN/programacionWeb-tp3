package JPA;

import com.google.gson.annotations.Expose;

import javax.persistence.*;


@Entity
@Table(name = "proveedor", schema = "public", catalog = "tienda")
public class ProveedorEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@Expose
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_proveedor")
	@SequenceGenerator(name = "seq_proveedor", sequenceName = "seq_proveedor")
	private long id;

	@Basic
	@Column(name = "descripcion", nullable = true, insertable = true, updatable = true, length = 100)
	@Expose
	private String descripcion;


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

}
