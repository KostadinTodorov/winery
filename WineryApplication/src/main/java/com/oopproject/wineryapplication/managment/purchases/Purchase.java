package com.oopproject.wineryapplication.managment.purchases;

import com.oopproject.wineryapplication.access.entities.entity.Entity;

public interface Purchase<T extends Entity> {
    T buy();
}
