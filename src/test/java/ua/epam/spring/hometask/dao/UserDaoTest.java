package ua.epam.spring.hometask.dao;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import ua.epam.spring.hometask.dao.id.IdGenerator;
import ua.epam.spring.hometask.domain.User;

public class UserDaoTest {
	private static final Long ID = 1L;
	private static final String NAME = "name";
	private static final Long ID_2 = 2L;
	private static final String NAME_2 = "name_2";
	
	private IdGenerator idGenerator;
	
	private UserDao dao;
	
	@Before
	public void setUp() {
		idGenerator = Mockito.mock(IdGenerator.class);
		dao = new UserDao(idGenerator);
		Mockito.when(idGenerator.generateId(Matchers.anyCollection())).thenReturn(ID);
	}
	
	@Test
	public void shouldGenerateIdForNewUserWithoudIdWhenSaveCalled() {
		// GIVEN
		
		// WHEN
		dao.save(createUser(null, NAME));
		final User savedUser = dao.getById(ID);
		// THEN
		Assert.assertEquals(savedUser.getFirstName(), NAME);
		Assert.assertEquals(savedUser.getId(), ID);
	}
	
	@Test
	public void shouldSaveUsertWhenSaveCalled() {
		// GIVEN
		
		// WHEN
		dao.save(createUser(ID, NAME));
		final User savedUser = dao.getById(ID);
		// THEN
		Assert.assertEquals(savedUser.getFirstName(), NAME);
		Assert.assertEquals(savedUser.getId(), ID);
	}
	
	@Test
	public void shouldGetByIdReturnUserWhenGetByIdCalled() {
		// GIVEN
		
		// WHEN
		dao.save(createUser(ID, NAME));
		dao.save(createUser(ID_2, NAME_2));
		final User savedUser = dao.getById(ID_2);
		// THEN
		Assert.assertEquals(savedUser.getFirstName(), NAME_2);
		Assert.assertEquals(savedUser.getId(), ID_2);
	}
	
	@Test
	public void shouldRemoveUserWhenRemoveCalled() {
		// GIVEN
		final User User = createUser(ID, NAME); 
		dao.save(User);
		dao.save(createUser(ID_2, NAME_2));
		
		// WHEN
		dao.remove(User);
		final User removedUser = dao.getById(ID);
		
		// THEN
		Assert.assertNull(removedUser);
	}
	
	@Test
	public void shouldGetAllReturnAllUserWhenGetAllCalled() {
		// GIVEN
		dao.save(createUser(ID, NAME));
		dao.save(createUser(ID_2, NAME_2));
		
		// WHEN
		final Collection<User> savedUser = dao.getAll();
		// THEN
		Assert.assertEquals(2, savedUser.size());
	}

	
	private User createUser(Long id, String name) {
		User user = new User();
		user.setId(id);
		user.setFirstName(name);
		return user;
	}
}
