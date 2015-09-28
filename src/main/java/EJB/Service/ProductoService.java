package EJB.Service;

import JPA.ProductoEntity;
import JPA.ProveedorEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by szalimben on 23/09/15.
 */
@Stateless
public class ProductoService extends Service<ProductoEntity> {

	@EJB
	ProductoService productoService;

	/**
	 * Metodo para obtener los productos cuyo stock sean menores al stock minimo
	 *
	 * @param stockMinimo
	 * 		Cantidad minima del stock que se desea mantenar
	 *
	 * @return Lista de productos que
	 */
	@SuppressWarnings("unchecked")
	public List<ProductoEntity> getProductosCantidadMinima(int stockMinimo) {

		long stock = ((long) stockMinimo);
		Query query = em.createQuery("SELECT p FROM ProductoEntity p WHERE p.stock <= :stockMinimo");
		query.setParameter("stockMinimo", stock);
		return query.getResultList();

	}

	/**
	 * Metodo para obtener la lista de todos los productos que posee un proveedor
	 *
	 * @param proveedor
	 * 		ProveedorEntity
	 *
	 * @return Lista de los productos que pertenecen al proveedor pasado como parametro
	 */
	public List<ProductoEntity> getProductosByProveedor(ProveedorEntity proveedor) {

		Query query = em.createQuery("SELECT p FROM ProductoEntity  p WHERE p.proveedor = :proveedor");
		query.setParameter("proveedor", proveedor);

		return query.getResultList();

	}

}




