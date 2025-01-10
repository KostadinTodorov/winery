package com.oopproject.wineryapplication.access.entities.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class EntityFieldMap{

    private Field[] fields;
    private Map<String, Type> map;
    Class<?> clazz;

    public EntityFieldMap(Class<?> clazz) {
        this.clazz = clazz;
        fields = this.clazz.getDeclaredFields();
    }

    public void setFields() {
        fields = clazz.getDeclaredFields();
    }

    public Field[] getFields() {
        return fields;
    }

    public Map<String, Type> getMap(){
        if (map == null) {
            map = new HashMap<>();
            for (Field field : fields) {
                map.put(field.getName(), field.getType());
            }
        }
        return map;
    }
}
