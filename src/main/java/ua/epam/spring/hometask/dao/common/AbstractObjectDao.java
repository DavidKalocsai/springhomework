package ua.epam.spring.hometask.dao.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import ua.epam.spring.hometask.domain.DomainObject;

/**
 * Abstract class to handle methods of fake abstract object Dao. Each
 * implementation extends this.
 *
 * @param <T> Type of the object that will be stored in the fake dao. The type
 *        extends DomainObject which forces the object ot have getId method.
 */
public abstract class AbstractObjectDao<T extends DomainObject> implements ObjectDao<T> {
	private final Map<Long, T> db = new HashMap<>();

	@Override
	public T save(final T object) {
		db.put(object.getId(), object);
		return object;
	}

	@Override
	public T getById(final Long id) {
		return db.get(id);
	}

	@Override
	public void remove(@Nonnull T object) {
		db.remove(object.getId());
	}

	@Override
	public Collection<T> getAll() {
		return db.values();
	}
}
