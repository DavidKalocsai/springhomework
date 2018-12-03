package ua.epam.spring.hometask.service.impl;

import java.util.Collection;
import java.util.Optional;

import ua.epam.spring.hometask.dao.ObjectDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

public class DefaultUserService implements UserService {
	private ObjectDao<User> userDao;
	
	public DefaultUserService(final ObjectDao<User> userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User save(final User user) {
		return userDao.save(user);
	}

	@Override
	public void remove(final User user) {
		userDao.remove(user);
	}

	@Override
	public User getById(final Long id) {
		return userDao.getById(id);
	}

	@Override
	public Collection<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public User getUserByEmail(final String email) {
		return findEmail(email);
	}
	
	private User findEmail(final String email) {
		final Collection<User> users = getAll();
		final Optional<User> foundUser = users.stream().filter(p -> p.getEmail().equals(email)).findFirst();
		return foundUser.orElse(null);
	}
}
