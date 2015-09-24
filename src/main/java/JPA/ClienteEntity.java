package JPA;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

/**
 * Created by szalimben on 22/09/15.
 */
@Entity
@Table(name = "cliente", schema = "public", catalog = "tienda")
public class ClienteEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@Expose
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
	@SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente")
	private Long id;

	@Basic
	@Column(name = "nombre", nullable = true, insertable = true, updatable = true, length = 100)
	@Expose
	private String nombre;

	@Basic
	@Column(name = "cedula_identidad", nullable = false, insertable = true, updatable = true)
	@Expose
	private Long cedulaIdentidad;

	@OneToMany(mappedBy = "cliente")
	@Expose
	private List<VentaEntity> ventas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCedulaIdentidad() {
		return cedulaIdentidad;
	}

	public void setCedulaIdentidad(Long cedulaIdentidad) {
		this.cedulaIdentidad = cedulaIdentidad;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		ClienteEntity that = (ClienteEntity) o;

		if(id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if(nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) {
			return false;
		}
		if(cedulaIdentidad != null ? !cedulaIdentidad.equals(that.cedulaIdentidad) : that.cedulaIdentidad != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
		result = 31 * result + (cedulaIdentidad != null ? cedulaIdentidad.hashCode() : 0);
		return result;
	}

	public List<VentaEntity> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentaEntity> ventas) {
		this.ventas = ventas;
	}
}
