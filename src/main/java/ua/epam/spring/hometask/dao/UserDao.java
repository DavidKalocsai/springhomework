package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.dao.id.IdGenerator;
import ua.epam.spring.hometask.domain.User;

public class UserDao extends AbstractObjectDao<User> {

	public UserDao(final IdGenerator idGenerator) {
		super(idGenerator);
	}
}
