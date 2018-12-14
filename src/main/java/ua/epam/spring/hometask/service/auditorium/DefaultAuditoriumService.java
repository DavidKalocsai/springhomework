package ua.epam.spring.hometask.service.auditorium;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import ua.epam.spring.hometask.dao.common.ObjectDao;
import ua.epam.spring.hometask.domain.Auditorium;

public class DefaultAuditoriumService implements AuditoriumService {
	
	private ObjectDao<Auditorium> auditoriumDao;
	
	public DefaultAuditoriumService(final ObjectDao<Auditorium> auditoriumDao) {
		this.auditoriumDao = auditoriumDao;
	}

	@Override
	public Set<Auditorium> getAll() {
		final Set<Auditorium> auditoriumSet = new HashSet<>();
		auditoriumSet.addAll(auditoriumDao.getAll());
		return auditoriumSet;
	}

	@Override
	public Auditorium getByName(String name) {
		final Optional<Auditorium> auditorium = auditoriumDao.getAll().stream().filter(t -> name.equals(t.getName())).findFirst();
		return auditorium.orElse(null);
	}

}
