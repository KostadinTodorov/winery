package com.oopproject.wineryapplication.access.daos.dao;

import com.oopproject.wineryapplication.access.entities.entity.Entity;
import org.hibernate.Session;

import java.util.List;

/**
 * Generic Data Access Object (DAO) interface for CRUD operations.
 *
 * <p>This interface provides CRUD methods for working with data entities. It is designed to be implemented by concrete DAO
 * classes specific to a particular {@code Entity}.</p>
 * <p>The generic class {@code <T>} has to extend the {@code abstract class Entity}. It represents a database table the date of which is mapped on to a data entity class extending {@code Entity}. </p>
 * <ul>
 *     The DAO {@code interface} provides methods contracts for
 *     <li>{@code T get{int id}} and {@code List<T> getAll()} are used to 'get' information out of the data source table.</li>
 *     <li>For making changes to the data source there are methods {@code boolean add(T t)} and {@code boolean update(int id, T t)} for adding and updating rows mapped in a given {@code T t} entity. The method {@code T insert(T t)} servers for both updating and adding.</li>
 *     <li>For deleting rows {@code boolean delete(int id)}</li>
 * </ul>
 * <p>A contract for creating a session to a certain data source</p>
 * @param <T> the type of the entity this DAO will handle
 */
public interface Dao<T extends Entity> {

    /**
     * Retrieves a concrete entity from the generic type {@code T} by its given ID.
     *
     * @param id the {@code int} ID of the entity the method retrieves from the data source. It represents the primaryKey of a row. The PK has to be of type int.
     * @return an instance of {@code T} entity with the specified ID, or {@code null} if no row with PK equaling {@code id} is found.
     */
    public T get(int id);

    /**
     * Retrieves all entities mapped from the rows in a table.
     *
     * @return a {@code List<T>} of all entities
     */
    public List<T> getAll();

    /**
     * Adds a new entity to the data source. The entity maps onto a new row in a table.
     *
     * @param entity the entity to add. The {@code T entity} could have an id represented in the data source but {@code entity.getId()} will be ignored. Tha database will generate an ID for the row.
     * @return {@code true} if the entity was added successfully, {@code false} otherwise
     */
    public boolean add(T entity);

    /**
     * Inserts a new entity into the data source and returns the inserted entity.
     *
     * <p>This method can be used for adding or updating an entity.</p>
     *
     * @param entity the entity to insert. The {@code T entity} is inserted into a table row with the same PK as {@code T entity}'s ID. If no such ID exists in the represented table or the entities ID is {@code null} the entity is added as a new row.
     * @return the inserted entity, potentially modified (e.g., with a generated ID)
     */
    public T insert(T entity);

    /**
     * Updates an existing entity in the data source by its ID.
     *
     * @param id the ID of the entity to update
     * @param entity the new entity that is assigned to the row with PK equal to {@code id}. The {@code T entity} could have an id represented in the data source but {@code entity.getId()} will be ignored. Tha information will be added according to the value of {@code int id}.
     * @return {@code true} if the entity was updated successfully, {@code false} otherwise
     */
    public boolean update(int id, T entity);

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

