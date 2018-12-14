package ua.epam.spring.hometask.dao.common;

import java.util.Collection;

import javax.annotation.Nonnull;

public interface ObjectDao<T> {
    /**
     * Saving new object to storage or updating existing one
     * 
     * @param object
     *            Object to save
     * @return saved object with assigned id
     */
    public T save(@Nonnull T object);

    /**
     * Retrieve object by id
     * 
     * @param object
     *            Object to save
     * @return saved object with assigned id
     */
    public T getById(@Nonnull Long object);
    
    
    /**
     * Removing object from storage
     * 
     * @param object
     *            Object to remove
     */
    public void remove(@Nonnull T object);

    /**
     * Getting all objects from storage
     * 
     * @return collection of objects
     */
    public @Nonnull Collection<T> getAll();
}
