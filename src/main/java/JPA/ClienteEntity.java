package JPA;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

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
	private long id;

	@Basic
	@Column(name = "nombre", nullable = true, insertable = true, updatable = true, length = 100)
	@Expose
	private String nombre;

	@Basic
	@Column(name = "cedula_identidad", nullable = false, insertable = true, updatable = true)
	@Expose
	private String cedulaIdentidad;

	public ClienteEntity() {
		// Constructor por defecto
	}

	// Constructor
	public ClienteEntity (String nombre, String cedulaIdentidad) {
		this.nombre = nombre;
		this.cedulaIdentidad = cedulaIdentidad;
	}

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

	public String getCedulaIdentidad() {
		return cedulaIdentidad;
	}

	public void setCedulaIdentidad(String cedulaIdentidad) {
		this.cedulaIdentidad = cedulaIdentidad;
	}

}
