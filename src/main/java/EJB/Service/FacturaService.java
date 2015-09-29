package EJB.Service;

import EJB.Helper.FacturasResponse;
import JPA.FacturaEntity;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Servicios para la gestion de facturas
 * <p/>
 * Created by szalimben on 23/09/15.
 */
@Stateless
public class FacturaService extends Service<FacturaEntity> {

	public Object getFacturas(MultivaluedMap<String, String> queryParams) {

		FacturasResponse response = new FacturasResponse();
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
		if(queryParams.getFirst("monto") != null) {
			ordenarPorColumna = "monto";
			ordenDeOrdenacion = queryParams.getFirst("monto");
		}
		else if(queryParams.getFirst("fecha") != null) {
			ordenarPorColumna = "fecha";
			ordenDeOrdenacion = queryParams.getFirst("fecha");
		}

		// Iniciamos las varialles para el filtrado
		String by_all_attributes = queryParams.getFirst("by_all_attributes");
		String by_monto = queryParams.getFirst("by_monto");
		String by_fecha = queryParams.getFirst("by_fecha");

		if(by_fecha == null) {
			by_fecha = "";
		}

		if(by_monto == null) {
			by_monto = "";
		}

		if(by_all_attributes == null) {
			by_all_attributes = "";
		}

		/* Creamos el query para la consulta */
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<FacturaEntity> criteriaQuery = criteriaBuilder.createQuery(FacturaEntity.class);
		Root<FacturaEntity> facturas = criteriaQuery.from(FacturaEntity.class);

		// Filtrado por todas las columnas
		Predicate filtradoPorAllAttributes = criteriaBuilder.or(criteriaBuilder.like(facturas.<String>get("monto"), "%" + by_all_attributes + "%"),
		                                                        criteriaBuilder.like(facturas.<String>get("fecha"), "%" + by_all_attributes + "%"));

		// Filtrado por columna
		Predicate filtradoPorColumna = criteriaBuilder.and(criteriaBuilder.like(facturas.<String>get("monto"), "%" + by_monto + "%"),
		                                                   criteriaBuilder.like(facturas.<String>get("fecha"), "%" + by_fecha + "%"));

		// Fijamos la Ordenacion
		if("asc".equals(ordenDeOrdenacion)) {
			criteriaQuery.multiselect( facturas.<String>get("monto"), facturas.<String>get("fecha"));
			criteriaQuery.where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.asc(facturas.get(ordenarPorColumna)));
		}
		else {
			criteriaQuery.multiselect(facturas.<String>get("proveedor"), facturas.<String>get("monto"), facturas.<String>get("fecha"));
			criteriaQuery.select(facturas).where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.desc(facturas.get(ordenarPorColumna)));
		}

		Integer page;
		page = Integer.valueOf(queryParams.getFirst("page")) - 1;

		response.setEntidades(em.createQuery(criteriaQuery).setMaxResults(getMeta().getPage_size().intValue()).setFirstResult(page * getMeta().getPage_size().intValue()).getResultList());
		response.setMeta(getMeta());
		return response;


	}


}
