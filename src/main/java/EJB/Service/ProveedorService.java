package EJB.Service;

import EJB.Helper.ProveedorResponse;
import JPA.ProveedorEntity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Servicios para la gestion de proveedores
 * <p>
 * Created by szalimben on 23/09/15.
 */
@Stateless
public class ProveedorService extends Service<ProveedorEntity> {


    /**
     * Cantidad de Registros
     *
     * @return Cantidad total de Registros
     */
    public Long getCount() {
        Query query = em.createNamedQuery("proveedor.totalRegisters");
        Long count = (Long) query.getSingleResult();
        return count;
    }

    public Object getProveedores(MultivaluedMap<String, String> queryParams) {

        ProveedorResponse response = new ProveedorResponse();
        inicializarMeta();
        getMeta().setTotal(this.getCount());
        getMeta().calculateToTalPages();

        /**
         * Variables default values for the column sort
         */
        String ordenarPorColumna = "id";
        String ordenDeOrdenacion = "asc";

        /**
         * Retrieve one or none of the URI query params that have the column name and sort order values
         */
        if (queryParams.getFirst("descripcion") != null) {
            ordenarPorColumna = "descripcion";
            ordenDeOrdenacion = queryParams.getFirst("descripcion");
        }

        // Iniciamos las varialles para el filtrado
        String by_all_attributes = queryParams.getFirst("by_all_attributes");
        String by_descripcion = queryParams.getFirst("by_descripcion");

        if (by_descripcion == null) {
            by_descripcion = "";
        }

        if (by_all_attributes == null) {
            by_all_attributes = "";
        }

        /* Creamos el query para la consulta */
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ProveedorEntity> criteriaQuery = criteriaBuilder.createQuery(ProveedorEntity.class);
        Root<ProveedorEntity> proveedores = criteriaQuery.from(ProveedorEntity.class);

        // Filtrado por todas las columnas
        Predicate filtradoPorAllAttributes = criteriaBuilder.or(criteriaBuilder.like(proveedores.<String>get("descripcion"), "%" + by_all_attributes + "%"));

        // Filtrado por columna
        Predicate filtradoPorColumna = criteriaBuilder.and(criteriaBuilder.like(proveedores.<String>get("descripcion"), "%" + by_descripcion + "%"));

        // Fijamos la Ordenacion
        if ("asc".equals(ordenDeOrdenacion)) {
            criteriaQuery.multiselect(proveedores.<String>get("descripcion"));
            criteriaQuery.where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.asc(proveedores.get(ordenarPorColumna)));
        } else {
            criteriaQuery.multiselect(proveedores.<String>get("descripcion"));
            criteriaQuery.select(proveedores).where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.desc(proveedores.get(ordenarPorColumna)));
        }

        Integer page;
        page = Integer.valueOf(queryParams.getFirst("page")) - 1;

        response.setEntidades(em.createQuery(criteriaQuery).setMaxResults(getMeta().getPage_size().intValue()).setFirstResult(page * getMeta().getPage_size().intValue()).getResultList());
        response.setMeta(getMeta());
        return response;


    }
}
