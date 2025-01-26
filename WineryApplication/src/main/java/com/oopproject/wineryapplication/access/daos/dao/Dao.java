package com.oopproject.wineryapplication.access.daos.dao;

import com.oopproject.wineryapplication.access.entities.entity.Entity;
import org.hibernate.Session;

import java.util.List;

/**
 * Generic Data Access Object (DAO) interface for CRUD operations.
 *
 * <p>This interface provides common methods for working with data entities, such as fetching,
 * adding, updating, and deleting records. It is designed to be implemented by concrete DAO
 * classes specific to particular entity types.</p>
 *
 * @param <T> the type of the entity this DAO will handle
 */
public interface Dao<T extends Entity> {

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the ID of the entity to retrieve
     * @return the entity with the specified ID, or {@code null} if not found
     */
    public T get(int id);

    /**
     * Retrieves all entities managed by this DAO.
     *
     * @return a list of all entities
     */
    public List<T> getAll();

    /**
     * Adds a new entity to the data source.
     *
     * @param t the entity to add
     * @return {@code true} if the entity was added successfully, {@code false} otherwise
     */
    public boolean add(T t);

    /**
     * Inserts a new entity into the data source and returns the inserted entity.
     *
     * <p>This method is typically used when the entity is modified during insertion
     * (e.g., when the database generates an ID).</p>
     *
     * @param t the entity to insert
     * @return the inserted entity, potentially modified (e.g., with a generated ID)
     */
    public T insert(T t);

    /**
     * Updates an existing entity in the data source by its ID.
     *
     * @param id the ID of the entity to update
     * @param t the new state of the entity
     * @return {@code true} if the entity was updated successfully, {@code false} otherwise
     */
    public boolean update(int id, T t);

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     * @return {@code true} if the entity was deleted successfully, {@code false} otherwise
     */
    public boolean delete(int id);

    /**
     * Creates a session for interacting with the data source.
     *
     * <p>This method is used to manage transactions or maintain a persistent connection
     * to the data source.</p>
     *
     * @return a {@code Session} object for data operations
     * @throws RuntimeException if a session cannot be created
     */
    public Session createSession() throws RuntimeException;
}

