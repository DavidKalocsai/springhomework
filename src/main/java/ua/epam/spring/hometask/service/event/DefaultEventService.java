package ua.epam.spring.hometask.service.event;

import java.util.Collection;
import java.util.Optional;

import ua.epam.spring.hometask.dao.common.ObjectDao;
import ua.epam.spring.hometask.domain.Event;

public class DefaultEventService implements EventService {
	private ObjectDao<Event> eventDao;
	
	public DefaultEventService(final ObjectDao<Event> eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public Event save(Event event) {
		return eventDao.save(event);
	}

	@Override
	public void remove(Event event) {
		eventDao.remove(event);
	}

	@Override
	public Event getById(Long id) {
		return eventDao.getById(id);
	}

	@Override
	public Collection<Event> getAll() {
		return eventDao.getAll();
	}

	@Override
	public Event getByName(String name) {
		final Collection<Event> events = getAll();
		final Optional<Event> foundEvent = events.stream().filter(p -> p.getName().equals(name)).findFirst();
		return foundEvent.orElse(null);
	}
}
