package com.oopproject.wineryapplication.helpers;

import com.oopproject.wineryapplication.access.entities.entity.Entity;

// Ensures that there is only one abstract method. This allows it to be used in lambda expressions.
@FunctionalInterface
public interface EntityProvider {
    Entity provide();
}
