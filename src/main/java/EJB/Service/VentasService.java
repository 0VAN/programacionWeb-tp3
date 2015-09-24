package EJB.Service;

import EJB.Helper.Meta;
import EJB.Helper.VentasResponse;
import JPA.MODEL.VentaEntity;

import javax.ejb.Stateless;
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
@Stateless
public class VentasService extends Service<VentaEntity> {

    private VentasResponse response;
    private Meta meta;

    private void setMetaInf(){
        meta.setTotal((Integer) this.getCount());
        meta.setPage_size(10);
        meta.calculateToTalPages();
    }

    public void addVenta(VentaEntity venta) {
        super.add(venta);
    }

    public void deleteVenta(VentaEntity venta) {
        super.deleteByEntity(venta);
    }

    public void editVenta(VentaEntity venta) {
        super.update(venta);

    }

    public List getVentas() {
        Query query = em.createNamedQuery("venta.findAll");
        return query.getResultList();
    }

    public Object getCount() {
        Query query = em.createNamedQuery("venta.totalRegisters");
        return query.getSingleResult();
    }

    public Object getVenta(int id) {
        Query query = em.createNamedQuery("venta.findById").setParameter("id", id);
        return query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public Object getVentas(MultivaluedMap<String, String> queryParams) {
        response =  new VentasResponse();
        meta = new Meta();
        setMetaInf();

        /**
         * Variables default values for the column sort
         */
        String ordenarPorColumna = "id";
        String ordenDeOrdenacion = "asc";

        /**
         * Retrieve one or none of the URI query params that have the column name and sort order values
         */
        if (queryParams.getFirst("cliente") != null) {
            ordenarPorColumna = "cliente";
            ordenDeOrdenacion = queryParams.getFirst("cliente");
        } else if (queryParams.getFirst("monto") != null) {
            ordenarPorColumna = "monto";
            ordenDeOrdenacion = queryParams.getFirst("monto");
        } else if (queryParams.getFirst("fecha") != null) {
            ordenarPorColumna = "fecha";
            ordenDeOrdenacion = queryParams.getFirst("fecha");
        }

        String by_monto;
        String by_cliente;
        String by_fecha;
        String by_all_attributes;


        by_all_attributes = queryParams.getFirst("by_all_attributes");
        by_monto = queryParams.getFirst("by_monto");
        by_cliente = queryParams.getFirst("by_cliente");
        by_fecha = queryParams.getFirst("by_fecha");


        if (by_cliente == null)
            by_cliente = "";

        if (by_fecha == null)
            by_fecha = "";

        if (by_monto == null)
            by_monto = "";

        if (by_all_attributes == null)
            by_all_attributes = "";

        /* Creamos el query para la consulta */
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<VentaEntity> criteriaQuery = criteriaBuilder.createQuery(VentaEntity.class);
        Root<VentaEntity> ventas = criteriaQuery.from(VentaEntity.class);

        // Filtrado por todas las columnas
        Predicate filtradoPorAllAttributes = criteriaBuilder.or(
                criteriaBuilder.like(ventas.<String>get("monto"), "%" + by_all_attributes + "%"),
                criteriaBuilder.like(ventas.<String>get("cliente"), "%" + by_all_attributes + "%"),
                criteriaBuilder.like(ventas.<String>get("fecha"), "%" + by_all_attributes + "%")
        );

        // Filtrado por columna
        Predicate filtradoPorColumna = criteriaBuilder.and(
                criteriaBuilder.like(ventas.<String>get("monto"), "%" + by_monto + "%"),
                criteriaBuilder.like(ventas.<String>get("cliente"), "%" + by_cliente + "%"),
                criteriaBuilder.like(ventas.<String>get("fecha"), "%" + by_fecha + "%")
        );

        if ("asc".equals(ordenDeOrdenacion))
            criteriaQuery.select(ventas).where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.asc(ventas.get(ordenarPorColumna)));
        else
            criteriaQuery.select(ventas).where(filtradoPorAllAttributes, filtradoPorColumna).orderBy(criteriaBuilder.desc(ventas.get(ordenarPorColumna)));


        Integer page;
        page = Integer.valueOf(queryParams.getFirst("page")) - 1;

        response.setEntidades(em.createQuery(criteriaQuery).setMaxResults(meta.getPage_size()).setFirstResult(page * meta.getPage_size()).getResultList());
        response.setMeta(meta);
        return response;
    }


}