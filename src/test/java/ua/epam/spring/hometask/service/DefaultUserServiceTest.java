package ua.epam.spring.hometask.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.epam.spring.hometask.dao.ObjectDao;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.impl.DefaultUserService;

public class DefaultUserServiceTest {
	private static final Long ID_1 = 1L;
	private static final Long ID_2 = 2L;
	private static final Long ID_3 = 3L;
	private static final String EMAIL_1 = "email_1";
	private static final String EMAIL_2 = "email_2";
	private static final String EMAIL_3 = "email_3";
	private static final String NOT_EXISTING_EMAIL = "email_not_existing";

	private ObjectDao<User> userDao;
	private User user;

	@Before
	public void setUp() {
		userDao = Mockito.mock(UserDao.class);
		user = Mockito.mock(User.class);
	}

	@Test
	public void save() {
		// GIVEN
		// WHEN
		new DefaultUserService(userDao).save(user);
		// THEN
		Mockito.verify(userDao, Mockito.times(1)).save(user);
	}

	@Test
	public void remove() {
		// GIVEN
		// WHEN
		new DefaultUserService(userDao).remove(user);
		// THEN
		Mockito.verify(userDao, Mockito.times(1)).remove(user);
	}

	@Test
	public void getById() {
		// GIVEN
		// WHEN
		new DefaultUserService(userDao).getById(ID_1);
		// THEN
		Mockito.verify(userDao, Mockito.times(1)).getById(ID_1);
	}

	@Test
	public void getAll() {
		// GIVEN
		// WHEN
		new DefaultUserService(userDao).getAll();
		// THEN
		Mockito.verify(userDao, Mockito.times(1)).getAll();
	}

	@Test
	public void shouldFindUserByEmail() {
		// GIVEN
		Mockito.when(userDao.getAll()).thenReturn(createUsers());
		// WHEN
		final User actualUser = new DefaultUserService(userDao).getUserByEmail(EMAIL_2);
		// THEN
		Mockito.verify(userDao, Mockito.times(1)).getAll();
		Assert.assertEquals(ID_2, actualUser.getId());
	}

	@Test
	public void shouldReturnNullWhenEmailDoesNotExistInList() {
		// GIVEN
		Mockito.when(userDao.getAll()).thenReturn(createUsers());
		// WHEN
		final User actualUser = new DefaultUserService(userDao).getUserByEmail(NOT_EXISTING_EMAIL);
		// THEN
		Mockito.verify(userDao, Mockito.times(1)).getAll();
		Assert.assertNull(actualUser);
	}

	private List<User> createUsers() {
		List<User> users = new ArrayList<>();
		users.add(createUser(ID_1, EMAIL_1));
		users.add(createUser(ID_2, EMAIL_2));
		users.add(createUser(ID_3, EMAIL_3));
		return users;
	}

	private User createUser(final Long id, final String email) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		return user;
	}
}
