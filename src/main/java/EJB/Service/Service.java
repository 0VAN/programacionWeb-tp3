package EJB.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;


/**
 * Created by szalimben on 22/09/15.
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Service<T> implements IService<T> , Serializable {

	@PersistenceContext(unitName="PU")
	protected EntityManager em;

	/**
	 * Metodo para borrar una entidad
	 *
	 * @param id
	 *          Identificador de la entidad
	 * @param clazz
	 *          Clase de la entidad
	 * @return <b>True</b> Si el borrado fue exitoso, <b>False</b> caso contrario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean delete(int id, Class<T> clazz) {
		try{
			T data = em.find(clazz, id);
			if(data != null) {
				em.remove(data);
				System.out.println("Borrado exitoso");
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error al borrar la entidad");
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Metodo para eliminar una entidad pasada como parametro la misma entidad
	 *
	 * @param entity
	 *          Entidad que se desea borrar
	 * @return <b>True</b> Si el borrado fue exitoso, <b>False</b> caso contrario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean deleteByEntity(T entity) {
		try{
			em.remove(entity);
			System.out.println("Borrado exitoso");
			return true;
		} catch (Exception e) {
			System.out.println("Error al borrar la entidad");
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Metodo para actualizar una entidad
	 *
	 * @param entity
	 *          Entidad con los datos actualizados
	 * @return <b>True</b> Si la actualizacion fue exitosa, <b>False</b> caso contrario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean update(T entity) {
		try{
			em.merge(entity);
			System.out.println("La entidad se actualizo exitosamente");
			return true;
		} catch (Exception e) {
			System.out.println("Error al actualizar la entidad");
		}

		return false;
	}

	/**
	 * Metodo para agregar una nueva entidad
	 *
	 * @param entity
	 *          Entidad que se desea agregar
	 * @return <b>True</b> Si la creacion fue exitosa, <b>False</b> caso contrario
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean add(T entity) {
		try {
			em.persist(entity);
			System.out.println("Creacion exitosa");
			return true;
		} catch (Exception e ) {
			System.out.println("Error al crear la entidad");
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Metodo para encontrar una entidad
	 *
	 * @param id
	 *          Identificador unico
	 * @param clazz
	 *          Clase de la entidad buscada
	 * @return Entidad buscada
	 */
	public T find(int id, Class<T> clazz) {
		return em.find(clazz, (long) id);
	}




}
