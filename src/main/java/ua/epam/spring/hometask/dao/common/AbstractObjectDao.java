package ua.epam.spring.hometask.dao.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import ua.epam.spring.hometask.domain.DomainObject;

public abstract class AbstractObjectDao<T extends DomainObject> implements ObjectDao<T> {
	private final Map<Long, T> db = new HashMap<>();
	
	@Override
	public T save(final T object) {
		db.put(object.getId(),object);
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
