package EJB;

import JPA.VentasEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by alex on 31/08/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Stateless
public class VentasService {

    @PersistenceContext(unitName = "PU")
    private EntityManager entityManager;

    private Pagination paginationInfo;


    private void setMetaInf() {
        paginationInfo = new Pagination();
        paginationInfo.setTotal(100);
        paginationInfo.setPage_size(10);
        paginationInfo.setTotal_pages(20);
    }

    public void addVenta(VentasEntity ventasEntity) {
        entityManager.persist(ventasEntity);
    }

    public void deleteVenta(VentasEntity ventasEntity) {
        entityManager.remove(ventasEntity);
    }

    public void editVenta(VentasEntity ventasEntity) {
        entityManager.merge(ventasEntity);
    }

    public List getVentas() {
        Query query = entityManager.createNamedQuery("ventas.findAll");
        return query.getResultList();
    }

    public Object getVenta(int id) {
        Query query = entityManager.createNamedQuery("ventas.findById").setParameter("id", id);
        setMetaInf();
        return new VentasResponse(query.getSingleResult(),paginationInfo);
    }


    public Object getVentas(MultivaluedMap<String, String> queryParams) {
        /**
         * Variables default values for the column sort
         */
        String ordenarPorColumna = "id";
        String ordenDeOrdenacion = "asc";

        /**
         * Retrieve one or none of the URI query params that have the column name and sort order values
         */
        if (queryParams.getFirst("numero") != null) {
            ordenarPorColumna = "numero";
            ordenDeOrdenacion = queryParams.getFirst("numero");
        } else if (queryParams.getFirst("nombreCliente") != null) {
            ordenarPorColumna = "nombreCliente";
            ordenDeOrdenacion = queryParams.getFirst("nombreCliente");
        } else if (queryParams.getFirst("montoTotal") != null) {
            ordenarPorColumna = "montoTotal";
            ordenDeOrdenacion = queryParams.getFirst("montoTotal");
        } else if (queryParams.getFirst("rucCliente") != null) {
            ordenarPorColumna = "rucCliente";
            ordenDeOrdenacion = queryParams.getFirst("rucCliente");
        } else if (queryParams.getFirst("fecha") != null) {
            ordenarPorColumna = "fecha";
            ordenDeOrdenacion = queryParams.getFirst("fecha");
        }


        String by_numero;
        String by_monto_total;
        String by_ruc_cliente;
        String by_nombre_cliente;
        String by_fecha;
        String by_all_attributes;

        by_numero = queryParams.getFirst("by_numero");
        by_monto_total = queryParams.getFirst("by_montoTotal");
        by_ruc_cliente = queryParams.getFirst("by_rucCliente");
        by_nombre_cliente = queryParams.getFirst("by_nombreCliente");
        by_fecha = queryParams.getFirst("by_fecha");
        by_all_attributes = queryParams.getFirst("by_all_attributes");


        if (by_nombre_cliente == null)
            by_nombre_cliente = "";

        if (by_fecha == null)
            by_fecha = "";

        if (by_monto_total == null)
            by_monto_total = "";

        if (by_ruc_cliente == null)
            by_ruc_cliente = "";

        if (by_numero == null)
            by_numero = "";

        if (by_all_attributes == null)
            by_all_attributes = "";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VentasEntity> criteriaQuery = criteriaBuilder.createQuery(VentasEntity.class);
        Root<VentasEntity> ventas = criteriaQuery.from(VentasEntity.class);

        Predicate filtradoPorAllAttributes = criteriaBuilder.or(
                criteriaBuilder.like(ventas.<String>get("numero"), "%" + by_all_attributes + "%"),
                criteriaBuilder.like(ventas.<String>get("montoTotal"), "%" + by_all_attributes + "%"),
                criteriaBuilder.like(ventas.<String>get("nombreCliente"), "%" + by_all_attributes + "%"),
                criteriaBuilder.like(ventas.<String>get("rucCliente"), "%" + by_all_attributes + "%"),
                criteriaBuilder.like(ventas.<String>get("fecha"), "%" + by_all_attributes + "%")
        );

        Predicate filtradoPorColumna = criteriaBuilder.and(
                criteriaBuilder.like(ventas.<String>get("numero"), "%" + by_numero + "%"),
                criteriaBuilder.like(ventas.<String>get("montoTotal"), "%" + by_monto_total + "%"),
                criteriaBuilder.like(ventas.<String>get("nombreCliente"), "%" + by_nombre_cliente + "%"),
                criteriaBuilder.like(ventas.<String>get("rucCliente"), "%" + by_ruc_cliente + "%"),
                criteriaBuilder.like(ventas.<String>get("fecha"), "%" + by_fecha + "%")
        );

        if ("asc".equals(ordenDeOrdenacion))
            criteriaQuery.select(ventas).where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.asc(ventas.get(ordenarPorColumna)));
        else
            criteriaQuery.select(ventas).where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.desc(ventas.get(ordenarPorColumna)));

        Integer page;
        page = Integer.valueOf(queryParams.getFirst("page")) - 1;


        List listaDeVentas = entityManager.createQuery(criteriaQuery).setMaxResults(10).setFirstResult(page * 10).getResultList();
        setMetaInf();

        return new VentasResponse(listaDeVentas, paginationInfo);
    }


}
