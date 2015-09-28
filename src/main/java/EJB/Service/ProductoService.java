package EJB.Service;

import JPA.ProductoEntity;

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
	 *            Cantidad minima del stock que se desea mantenar
	 * @return Lista de productos que
	 */
	@SuppressWarnings("unchecked")
	public List<ProductoEntity> getProductosCantidadMinima(int stockMinimo) {


		Long stock = new Long(stockMinimo);

		Query query = em.createQuery("SELECT p FROM ProductoEntity p WHERE p.stock <= :stockMinimo");
		query.setParameter("stockMinimo", stock);
		return query.getResultList();
	}

}




