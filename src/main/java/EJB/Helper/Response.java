package EJB.Helper;

import java.util.List;

/**
 * Created by szalimben on 22/09/15.
 */
public class Response<T> {

	private List<T> entidades;
	private Meta meta;

	public List<T> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<T> entidades) {
		this.entidades = entidades;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}


}
