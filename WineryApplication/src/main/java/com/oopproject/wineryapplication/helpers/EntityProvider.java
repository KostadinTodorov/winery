package com.oopproject.wineryapplication.helpers;

import com.oopproject.wineryapplication.access.entities.entity.Entity;

@FunctionalInterface
public interface EntityProvider {
    Entity provide();
}
