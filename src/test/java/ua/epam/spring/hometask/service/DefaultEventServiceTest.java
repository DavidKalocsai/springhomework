package ua.epam.spring.hometask.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.ObjectDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.impl.DefaultEventService;

public class DefaultEventServiceTest {
	private static final Long ID_1 = 1L;
	private static final Long ID_2 = 2L;
	private static final Long ID_3 = 3L;
	private static final String NAME_1 = "name_1";
	private static final String NAME_2 = "name_2";
	private static final String NAME_3 = "name_3";
	private static final String NOT_EXISTING_NAME = "name_not_existing";

	private ObjectDao<Event> eventDao;
	private Event event;

	@Before
	public void setUp() {
		eventDao = Mockito.mock(EventDao.class);
		event = Mockito.mock(Event.class);
	}

	@Test
	public void save() {
		// GIVEN
		// WHEN
		new DefaultEventService(eventDao).save(event);
		// THEN
		Mockito.verify(eventDao, Mockito.times(1)).save(event);
	}

	@Test
	public void remove() {
		// GIVEN
		// WHEN
		new DefaultEventService(eventDao).remove(event);
		// THEN
		Mockito.verify(eventDao, Mockito.times(1)).remove(event);
	}

	@Test
	public void getById() {
		// GIVEN
		// WHEN
		new DefaultEventService(eventDao).getById(ID_1);
		// THEN
		Mockito.verify(eventDao, Mockito.times(1)).getById(ID_1);
	}

	@Test
	public void getAll() {
		// GIVEN
		// WHEN
		new DefaultEventService(eventDao).getAll();
		// THEN
		Mockito.verify(eventDao, Mockito.times(1)).getAll();
	}

	@Test
	public void shouldFindEventByEmail() {
		// GIVEN
		Mockito.when(eventDao.getAll()).thenReturn(createEvents());
		// WHEN
		final Event actualEvent = new DefaultEventService(eventDao).getByName(NAME_2);
		// THEN
		Mockito.verify(eventDao, Mockito.times(1)).getAll();
		Assert.assertEquals(ID_2, actualEvent.getId());
	}

	@Test
	public void shouldReturnNullWhenEmailDoesNotExistInList() {
		// GIVEN
		Mockito.when(eventDao.getAll()).thenReturn(createEvents());
		// WHEN
		final Event actualEvent = new DefaultEventService(eventDao).getByName(NOT_EXISTING_NAME);
		// THEN
		Mockito.verify(eventDao, Mockito.times(1)).getAll();
		Assert.assertNull(actualEvent);
	}

	private List<Event> createEvents() {
		List<Event> events = new ArrayList<>();
		events.add(createEvent(ID_1, NAME_1));
		events.add(createEvent(ID_2, NAME_2));
		events.add(createEvent(ID_3, NAME_3));
		return events;
	}

	private Event createEvent(final Long id, final String name) {
		Event event = new Event();
		event.setId(id);
		event.setName(name);
		return event;
	}
}
