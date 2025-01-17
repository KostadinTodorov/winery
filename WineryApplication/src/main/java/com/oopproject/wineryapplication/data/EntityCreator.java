package com.oopproject.wineryapplication.data;

import com.oopproject.wineryapplication.access.entities.entity.Entity;

import java.lang.reflect.Constructor;

public class EntityCreator {
    public static <T extends Entity> T createInstance(Class<T> entiryClass) {
        try {
            Constructor<T> constructor = entiryClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + entiryClass.getSimpleName(), e);
        }
    }
}