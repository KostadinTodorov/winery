package com.oopproject.wineryapplication.access.entities.entity;

public abstract class Entity {
    public abstract Integer getId();

    public abstract void setId(Integer id);

    @Override
    public String toString() {
        return getId().toString();
    }
}
