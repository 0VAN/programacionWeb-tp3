package JPA;

import com.google.gson.annotations.Expose;

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
	@Expose
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_proveedor")
	@SequenceGenerator(name = "seq_proveedor", sequenceName = "seq_proveedor")
	private Long id;

	@Basic
	@Column(name = "descripcion", nullable = true, insertable = true, updatable = true, length = 100)
	@Expose
	private String descripcion;

	@OneToMany(mappedBy = "proveedor")
	@Expose
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

	public List<CompraEntity> getCompras() {
		return compras;
	}

	public void setCompras(List<CompraEntity> compras) {
		this.compras = compras;
	}
}
