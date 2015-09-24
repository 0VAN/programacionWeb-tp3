package EJB.Service;

import javax.ejb.Local;

/**
 * Created by szalimben on 22/09/15.
 */
@Local
public interface IService<T> {

	boolean delete(int id, Class<T> clase) throws Throwable;

	boolean add(T entity);

	boolean update (T entity);

	T find (int id, Class<T> clazz);

}
