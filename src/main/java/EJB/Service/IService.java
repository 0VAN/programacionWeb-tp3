package EJB.Service;

/**
 * Created by szalimben on 22/09/15.
 */
public interface IService<T> {

	boolean delete(int id, Class<T> clase) throws Throwable;

	boolean add(T entity);

	boolean update (T entity);

	T find (int id, Class<T> clazz);

}
