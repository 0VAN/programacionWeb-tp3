package EJB.Service;

import JPA.MODEL.ProductoEntity;
import JPA.MODEL.SolicitudCompraEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by szalimben on 23/09/15.
 */
@Stateless
public class SolicitudCompraService extends Service<SolicitudCompraEntity> {


	private static final int cantidadMinima = 10;

	@EJB
	ProductoService productoService;

	@EJB
	SolicitudCompraService solicitudCompraService;

	/**
	 * Metodo para crear una nueva solicitud de compra
	 */
	public void crear() {

		List<ProductoEntity> productos = productoService.getProductosCantidadMinima(cantidadMinima);
		System.out.println("Creando Ordenes de compra");
		for(ProductoEntity producto : productos) {
			if(!yaExiste(producto)) {
				SolicitudCompraEntity entity = new SolicitudCompraEntity();
				entity.setFecha(new Date());
				entity.setProducto(producto);
				em.persist(entity);
			}
		}
	}

	/**
	 * Metodo que verifica que el producto no posee ya una solicitud de compra activa,
	 * en caso de que el producto posea una solicitud de compra ya no se creara
	 * una nueva
	 *
	 * @param producto ProductoEntity asociado
	 * @return Retorna <b> True</b> si ya existe una solicitud de compra asociada
	 * a dicho producto, <b>False</b> caso contrario
	 */
	public boolean yaExiste(ProductoEntity producto) {

		Query query = em.createQuery("SELECT sc FROM SolicitudCompraEntity sc WHERE sc.producto = :producto AND sc.atendido = false ");
		query.setParameter("producto", producto);

		return query.getResultList().isEmpty();
	}


}
