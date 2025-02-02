package com.oopproject.wineryapplication.access.entities.entity.contracts;

/**
 * Defines a contract for entities that can be represented as a string.
 *
 * This interface enforces the implementation of the {@code toString} method,
 * allowing objects to provide a meaningful string representation of their state.
 */
public interface EntityPrintable {
    /**
     * Returns a string representation of the object.
     *
     * The string representation typically includes the object's class name,
     * its unique identifier, and optionally additional relevant attributes.
     *
     * @return a string representation of the object
     */
    @Override
    String toString();
}
