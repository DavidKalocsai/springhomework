package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.dao.id.IdGenerator;
import ua.epam.spring.hometask.domain.Event;

public class EventDao extends AbstractObjectDao<Event> {

	public EventDao(final IdGenerator idGenerator) {
		super(idGenerator);
	}
}
