package ua.epam.spring.hometask.dao.id;

import java.util.Collection;

import javax.annotation.Nonnull;

/*
 * Interface to generate id to a new object before adding to the storage the first time.
 */
public interface IdGenerator {
	/**
	 * Generate id for a new entry.
	 * @param ids collection of keys
	 * @return id for the new entry;
	 */
	Long generateId(@Nonnull Collection<Long> ids);
}
