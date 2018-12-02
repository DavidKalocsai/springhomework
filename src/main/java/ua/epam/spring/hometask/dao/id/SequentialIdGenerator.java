package ua.epam.spring.hometask.dao.id;

import java.util.Collection;
import java.util.Collections;

public class SequentialIdGenerator implements IdGenerator {
	private static final Long ID_FOR_EMPTY_MAP = 1L; 

	@Override
	public Long generateId(Collection<Long> ids) {
		Long id = ID_FOR_EMPTY_MAP;
		if (!ids.isEmpty()) {
			id = Collections.max(ids) + 1;
		}
		return id;
	}
}
