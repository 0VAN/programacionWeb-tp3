package EJB.Service;

import EJB.Helper.ClienteResponse;
import JPA.ClienteEntity;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Servicios para la gestion de Clientes
 *
 * Created by szalimben on 23/09/15.
 */
@Stateless
public class ClienteService extends Service<ClienteEntity> {

	/**
	 * Retorna la entidad buscada por Id
	 *
	 * @param id
	 *          Identificador del elemento buscado
	 * @return Elemento cuyo identificador corresponda
	 */
	public Object getCliente(int id) {
		return find(id, ClienteEntity.class);
	}

	/**
	 * Metodo para obtener la lista de clientes por filtro y
	 * orden aplicado a las columnas
	 *
	 * @param queryParams
	 *          parametros de filtro y orden
	 * @return Lista de Clientes que coinciden con los parametros de filtro y orden
	 */
	public Object getClientes(MultivaluedMap<String, String> queryParams) {

		ClienteResponse response = new ClienteResponse();
		inicializarMeta();
		setMetaInf();

		/**
		 * Variables default values for the column sort
		 */
		String ordenarPorColumna = "id";
		String ordenDeOrdenacion = "asc";

		/**
		 * Retrieve one or none of the URI query params that have the column name and sort order values
		 */
		if(queryParams.getFirst("nombre") != null) {
			ordenarPorColumna = "nombre";
			ordenDeOrdenacion = queryParams.getFirst("nombre");
		}
		else if(queryParams.getFirst("cedulaIdentidad") != null) {
			ordenarPorColumna = "cedulaIdentidad";
			ordenDeOrdenacion = queryParams.getFirst("cedulaIdentidad");
		}

		// Iniciamos las variables parael filtrado
		String by_all_attributes = queryParams.getFirst("by_all_attributes");
		String by_cedula_identidad = queryParams.getFirst("by_cedula_identidad");
		String by_nombre = queryParams.getFirst("by_nombre");

		if(by_nombre == null) {
			by_nombre = "";
		}

		if(by_cedula_identidad == null) {
			by_cedula_identidad = "";
		}

		if(by_all_attributes == null) {
			by_all_attributes = "";
		}

        /* Creamos el query para la consulta */
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ClienteEntity> criteriaQuery = criteriaBuilder.createQuery(ClienteEntity.class);
		Root<ClienteEntity> clientes = criteriaQuery.from(ClienteEntity.class);

		// Filtrado por todas las columnas
		Predicate filtradoPorAllAttributes = criteriaBuilder.or(criteriaBuilder.like(clientes.<String>get("nombre"), "%" + by_all_attributes + "%"),
		                                                        criteriaBuilder.like(clientes.<String>get("cedulaIdentidad"), "%" + by_all_attributes + "%"));

		// Filtrado por columna
		Predicate filtradoPorColumna = criteriaBuilder.and(criteriaBuilder.like(clientes.<String>get("nombre"), "%" + by_nombre + "%"),
		                                                   criteriaBuilder.like(clientes.<String>get("cedulaIdentidad"), "%" + by_cedula_identidad + "%"));

		// Fijamos la Ordenacion
		if("asc".equals(ordenDeOrdenacion)) {
			criteriaQuery.multiselect(clientes.<String>get("nombre"), clientes.<String>get("cedulaIdentidad"));
			criteriaQuery.where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.asc(clientes.get(ordenarPorColumna)));
		}
		else {
			criteriaQuery.multiselect(clientes.<String>get("nombre"), clientes.<String>get("cedulaIdentidad"));
			criteriaQuery.select(clientes).where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.desc(clientes.get(ordenarPorColumna)));
		}

		Integer page;
		page = Integer.valueOf(queryParams.getFirst("page")) - 1;

		response.setEntidades(em.createQuery(criteriaQuery).setMaxResults(getMeta().getPage_size().intValue()).setFirstResult(page * getMeta().getPage_size().intValue()).getResultList());
		response.setMeta(getMeta());
		return response;
	}


}
