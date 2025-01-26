package com.oopproject.wineryapplication.access.entities.creator;

import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.access.entities.helper.EntityTypeNodeMapper;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityFactory {
    public Entity createEntity(Entity entity, Map<Field, Node> fieldNodeMap) {
        Set<Field> entityFields = entity.toNode(new EntityTypeNodeMapper(entity.getClass())).keySet();

        for (Field field : entityFields) {
            field.setAccessible(true);

            Node node = fieldNodeMap.get(field);

            Class<?> fieldType = field.getType();
            try {
                if (Entity.class.isAssignableFrom(fieldType)) {
                    ComboBox<Entity> entityComboBox = (ComboBox<Entity>) node;
                    if (entityComboBox.getValue() != null) {
                        field.set(entity, entityComboBox.getValue());
                    }
                } else {
                    setFieldValue(field, entity, node, fieldType);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    private void setFieldValue(Field field, Entity entity, Node node, Class<?> fieldType) throws IllegalAccessException {
        switch (node) {
            case TextField textField -> {
                String value = textField.getText();
                if (value != null && !value.isEmpty()) {
                    if (fieldType == String.class) {
                        field.set(entity, value);
                    } else if (fieldType == Short.class || fieldType == short.class) {
                        field.set(entity, Short.parseShort(value));
                    } else if (fieldType == Integer.class || fieldType == int.class) {
                        field.set(entity, Integer.parseInt(value));
                    } else if (fieldType == Long.class || fieldType == long.class) {
                        field.set(entity, Long.parseLong(value));
                    } else if (fieldType == Float.class || fieldType == float.class) {
                        field.set(entity, Float.parseFloat(value));
                    } else if (fieldType == Double.class || fieldType == double.class) {
                        field.set(entity, Double.parseDouble(value));
                    } else {
                        System.err.println("Unsupported field type: " + fieldType.getName());
                    }
                }
            }
            case CheckBox checkBox when (fieldType == Boolean.class || fieldType == boolean.class) ->
                    field.set(entity, checkBox.isSelected());
            case DatePicker datePicker when fieldType == java.time.LocalDate.class ->{
                if (datePicker.getValue() != null) {
                    field.set(entity, datePicker.getValue());
                }
            }
            case null, default -> throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
        }
    }
}