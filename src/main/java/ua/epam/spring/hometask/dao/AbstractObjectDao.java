package ua.epam.spring.hometask.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import ua.epam.spring.hometask.dao.id.IdGenerator;
import ua.epam.spring.hometask.domain.DomainObject;

public abstract class AbstractObjectDao<T extends DomainObject> implements ObjectDao<T> {
	final Map<Long, T> db = new HashMap<>();
	
	private IdGenerator idGenerator;
	
	public AbstractObjectDao(final IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}
	
	@Override
	public T save(final T object) {
		if (isIdSet(object)) {
			addId(object);
		}
		db.put(object.getId(), object);
		return object;
	}
	
	private boolean isIdSet(T object) {
		return object.getId() == null;
	}

	private void addId(final T object) {
		final Long id = idGenerator.generateId(db.keySet());
		object.setId(id);
	}	

	@Override
	public void remove(@Nonnull T object) {
		db.remove(object.getId());
	}

	@Override
	public T getById(Long id) {
		return db.get(id);
	}

	@Override
	public Collection<T> getAll() {
		return db.values();
	}	
}
