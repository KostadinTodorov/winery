package com.oopproject.wineryapplication.access.entities.entity.contracts;

import com.oopproject.wineryapplication.access.daos.dao.Dao;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;

/**
 * Represents an entity with a unique identifier and associated data access object.
 *
 * This interface defines a contract for classes representing entities
 * that can be stored, retrieved, and updated in a data source.
 *
 * Methods in this interface:
 * - Provide access to a unique identifier (`id`) associated with the entity.
 * - Allow retrieval of the Data Access Object (DAO) associated with the entity's type.
 */
public interface Entitiable {
    /**
     * Retrieves the unique identifier associated with the entity.
     *
     * @return the unique identifier of the entity as an {@code Integer}, or {@code null} if the identifier is not set.
     */
    Integer getId();

    /**
     * Sets the unique identifier for the entity.
     *
     * @param id the unique identifier to assign to the entity, represented as an {@code Integer}.
     *           This value can be {@code null}, which indicates that the entity may not have an assigned ID yet.
     */
    void setId(Integer id);

    /**
     * Retrieves the Data Access Object (DAO) associated with the entity's type.
     *
     * @return the DAO instance that provides CRUD operations and data management for the entity
     */
    Dao getDao();
}
