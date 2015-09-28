package JPA;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "factura", schema = "public", catalog = "tienda")
public class FacturaEntity {

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	@Expose
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_factura")
	@SequenceGenerator(name = "seq_factura", sequenceName = "seq_factura")
	private Long id;

	@Basic
	@Column(name = "monto", nullable = true, insertable = true, updatable = true)
	@Expose
	private String monto;

	@Basic
	@Column(name = "fecha", nullable = false, insertable = true, updatable = true)
	@Expose
	private String fecha;

	public FacturaEntity() {
		// Constructor por Defecto
	}

	// Constructor con parametros
	public FacturaEntity(String monto, String fecha) {
		this.monto = monto;
		this.fecha = fecha;

	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		FacturaEntity that = (FacturaEntity) o;

		if(id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if(monto != null ? !monto.equals(that.monto) : that.monto != null) {
			return false;
		}
		if(fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (monto != null ? monto.hashCode() : 0);
		result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
		return result;
	}

}
