package com.oopproject.wineryapplication.access.entities.creator;

import com.oopproject.wineryapplication.access.entities.entity.Entity;

/**
 * Defines a factory interface for creating instances of the {@link Entity} class.
 * Implementations of this interface are responsible for encapsulating the logic
 * required to create and initialize an entity, potentially using specific mappings
 * or other contextual information.
 */
public interface EntityFactory {
    /**
     * Creates and returns a new instance of the Entity class. The method is responsible
     * for initializing and populating the fields of the entity with the appropriate
     * values, which may involve mapping user-input data or other contextual information.
     *
     * @return a newly created and initialized instance of the Entity class
     */
    public Entity createEntity();
}
