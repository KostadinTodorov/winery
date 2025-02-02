package com.oopproject.wineryapplication.helpers.buttons;

import com.oopproject.wineryapplication.access.entities.entity.Entity;

/**
 * Functional interface for providing instances of {@link Entity}.
 * This is designed to facilitate the creation or retrieval of entity objects in scenarios
 * where dynamic or deferred instance creation is necessary, such as lambda expressions or method references.
 *
 * Implementing classes or lambda expressions must define the single abstract method {@link #provide()},
 * ensuring that an {@link Entity} object is returned.
 *
 * Common use cases include:
 * - Dependency injection scenarios where an {@link Entity} instance needs to be dynamically provided.
 * - Contexts where entities are created or loaded based on user interactions or system state.
 *
 * Since this interface is annotated with {@link FunctionalInterface}, it can be seamlessly
 * used in lambda expressions or method references, promoting concise and expressive code.
 */
// Ensures that there is only one abstract method. This allows it to be used in lambda expressions.
@FunctionalInterface
public interface EntityProvider {
    /**
     * Provides an instance of the {@link Entity}.
     *
     * This method is intended to be implemented by classes or used in lambda expressions
     * where an {@link Entity} object is dynamically supplied. The returned entity
     * can represent any specific type of entity within the system.
     *
     * @return an instance of {@link Entity}, which can be used in the context where this method is invoked
     */
    Entity provide();
}
