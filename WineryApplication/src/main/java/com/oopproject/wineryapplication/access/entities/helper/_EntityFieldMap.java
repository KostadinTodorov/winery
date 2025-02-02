package com.oopproject.wineryapplication.access.entities.helper;

import com.oopproject.wineryapplication.access.entities.entity.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

 class _EntityFieldMap {

    private Field[] fields;
    private Map<String, Type> map;
    Class<? extends Entity> clazz;

    public _EntityFieldMap(Class<? extends Entity> clazz) {
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
