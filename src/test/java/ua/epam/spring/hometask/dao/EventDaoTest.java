package ua.epam.spring.hometask.dao;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import ua.epam.spring.hometask.dao.id.IdGenerator;
import ua.epam.spring.hometask.domain.Event;

public class EventDaoTest {
	private static final Long ID = 1L;
	private static final String NAME = "name";
	private static final Long ID_2 = 2L;
	private static final String NAME_2 = "name_2";
	
	private IdGenerator idGenerator;
	
	private EventDao dao;
	
	@Before
	public void setUp() {
		idGenerator = Mockito.mock(IdGenerator.class);
		dao = new EventDao(idGenerator);
		Mockito.when(idGenerator.generateId(Matchers.anyCollection())).thenReturn(ID);
	}
	
	@Test
	public void shouldGenerateIdForNewEventWithoudIdWhenSaveCalled() {
		// GIVEN
		
		// WHEN
		dao.save(createEvent(null, NAME));
		final Event savedEvent = dao.getById(ID);
		// THEN
		Assert.assertEquals(savedEvent.getName(), NAME);
		Assert.assertEquals(savedEvent.getId(), ID);
	}
	
	@Test
	public void shouldSaveEventWhenSaveCalled() {
		// GIVEN
		
		// WHEN
		dao.save(createEvent(ID, NAME));
		final Event savedEvent = dao.getById(ID);
		// THEN
		Assert.assertEquals(savedEvent.getName(), NAME);
		Assert.assertEquals(savedEvent.getId(), ID);
	}
	
	@Test
	public void shouldGetByIdReturnEventWhenGetByIdCalled() {
		// GIVEN
		
		// WHEN
		dao.save(createEvent(ID, NAME));
		dao.save(createEvent(ID_2, NAME_2));
		final Event savedEvent = dao.getById(ID_2);
		// THEN
		Assert.assertEquals(savedEvent.getName(), NAME_2);
		Assert.assertEquals(savedEvent.getId(), ID_2);
	}
	
	@Test
	public void shouldRemoveEventWhenRemoveCalled() {
		// GIVEN
		final Event event = createEvent(ID, NAME); 
		dao.save(event);
		dao.save(createEvent(ID_2, NAME_2));
		
		// WHEN
		dao.remove(event);
		final Event removedEvent = dao.getById(ID);
		
		// THEN
		Assert.assertNull(removedEvent);
	}
	
	@Test
	public void shouldGetAllReturnAllEventWhenGetAllCalled() {
		// GIVEN
		dao.save(createEvent(ID, NAME));
		dao.save(createEvent(ID_2, NAME_2));
		
		// WHEN
		final Collection<Event> savedEvent = dao.getAll();
		// THEN
		Assert.assertEquals(2, savedEvent.size());
	}

	
	private Event createEvent(Long id, String name) {
		Event event = new Event();
		event.setId(id);
		event.setName(name);
		return event;
	}

}
