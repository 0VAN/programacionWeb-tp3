package EJB.Service;

import EJB.Helper.ComprasResponse;
import JPA.CompraEntity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Servicios para la gestion de las compras
 * <p>
 * Created by szalimben on 23/09/15.
 */
@Stateless
public class CompraService extends Service<CompraEntity> {


    /**
     * Retorna la entidad buscada por Id
     *
     * @param id Identificador del elemento buscado
     * @return Elemento cuyo identificador corresponda
     */
    public Object getCompra(int id) {
        return find(id, CompraEntity.class);
    }

    /**
     * Cantidad de Registros
     *
     * @return Cantidad total de Registros
     */
    public Long getCount() {
        Query query = em.createNamedQuery("compra.totalRegisters");
        Long count = (Long) query.getSingleResult();
        return count;
    }

    /**
     * Metodo para obtener la lista de entidades por filtro y
     * orden aplicado a las columnas
     *
     * @param queryParams parametros de filtro y orden
     * @return Lista de Clientes que coinciden con los parametros de filtro y orden
     */
    public Object getCompras(MultivaluedMap<String, String> queryParams) {

        ComprasResponse response = new ComprasResponse();
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
        if (queryParams.getFirst("proveedor") != null) {
            ordenarPorColumna = "proveedor";
            ordenDeOrdenacion = queryParams.getFirst("proveedor");
        } else if (queryParams.getFirst("monto") != null) {
            ordenarPorColumna = "monto";
            ordenDeOrdenacion = queryParams.getFirst("monto");
        } else if (queryParams.getFirst("fecha") != null) {
            ordenarPorColumna = "fecha";
            ordenDeOrdenacion = queryParams.getFirst("fecha");
        }

        // Iniciamos las varialles para el filtrado
        String by_all_attributes = queryParams.getFirst("by_all_attributes");
        String by_monto = queryParams.getFirst("by_monto");
        String by_proveedor = queryParams.getFirst("by_proveedor");
        String by_fecha = queryParams.getFirst("by_fecha");

        if (by_proveedor == null) {
            by_proveedor = "";
        }

        if (by_fecha == null) {
            by_fecha = "";
        }

        if (by_monto == null) {
            by_monto = "";
        }

        if (by_all_attributes == null) {
            by_all_attributes = "";
        }

        /* Creamos el query para la consulta */
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CompraEntity> criteriaQuery = criteriaBuilder.createQuery(CompraEntity.class);
        Root<CompraEntity> ventas = criteriaQuery.from(CompraEntity.class);

        // Filtrado por todas las columnas
        Predicate filtradoPorAllAttributes = criteriaBuilder.or(criteriaBuilder.like(ventas.<String>get("monto"), "%" + by_all_attributes + "%"),
                criteriaBuilder.like(ventas.<String>get("proveedor").<String>get("descripcion"), "%" + by_proveedor + "%"),
                criteriaBuilder.like(ventas.<String>get("fecha"), "%" + by_all_attributes + "%"));

        // Filtrado por columna
        Predicate filtradoPorColumna = criteriaBuilder.and(criteriaBuilder.like(ventas.<String>get("monto"), "%" + by_monto + "%"),
                criteriaBuilder.like(ventas.<String>get("proveedor").<String>get("descripcion"), "%" + by_proveedor + "%"),
                criteriaBuilder.like(ventas.<String>get("fecha"), "%" + by_fecha + "%"));

        // Fijamos la Ordenacion
        if ("asc".equals(ordenDeOrdenacion)) {
            criteriaQuery.multiselect(ventas.<String>get("proveedor"),
                    ventas.<String>get("monto"),
                    ventas.<String>get("fecha"));

            criteriaQuery.where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.asc(ventas.get(ordenarPorColumna)));
        } else {
            criteriaQuery.multiselect(ventas.<String>get("proveedor"),
                    ventas.<String>get("monto"),
                    ventas.<String>get("fecha"));

            criteriaQuery.select(ventas).where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.desc(ventas.get(ordenarPorColumna)));
        }

        Integer page;
        page = Integer.valueOf(queryParams.getFirst("page")) - 1;

        response.setEntidades(em.createQuery(criteriaQuery).setMaxResults(getMeta().getPage_size().intValue()).setFirstResult(page * getMeta().getPage_size().intValue()).getResultList());
        response.setMeta(getMeta());
        return response;


    }

}
