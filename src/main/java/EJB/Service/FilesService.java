package EJB.Service;

import JPA.MODEL.*;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nabil on 24/09/15.
 */

@Stateful
public class FilesService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ClienteEntity cliente;

    @Inject
    private CompraEntity compra;

    @Inject
    private ProveedorEntity proveedor;

    @Inject
    private CompraDetalleEntity compraDetalle;

    @Inject
    private ProductoEntity producto;

    private List<CompraDetalleEntity> listaDetalles = new ArrayList<CompraDetalleEntity>();

    @EJB
    Service service;

    public void addCliente(String nombre, String cedula) {
        try {
            cliente.setNombre(nombre);
            cliente.setCedulaIdentidad(Long.parseLong(cedula));
            em.persist(cliente);
        } catch (Exception e) {
            // se procesa la excepcion
        }
    }

    public void addCabeceraCompra(String fecha, String monto, String proveedorId){
        compra.setFecha(fecha);
        compra.setMonto(monto);
        proveedor = service.find(Integer.parseInt(proveedorId), ProveedorEntity.class);
        compra.setProveedor(proveedor);
    }

    public void addCompraDetalle(String productoId, String cantidad){
        producto = service.find(Integer.parseInt(productoId), ProductoEntity.class);
        compraDetalle.setProducto(producto);
        compraDetalle.setCantidad(cantidad);
        listaDetalles.add(compraDetalle);
    }

    public void addCompra(){
        compra.setDetalles(listaDetalles);
        em.persist(compra);
    }


}
