package com.oopproject.wineryapplication.access.entities.creator;

import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.access.entities.mappers.EntityFieldMapper;
import com.oopproject.wineryapplication.access.entities.mappers.EntityTypeNodeMapper;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * GenericEntityFactory is a concrete implementation of the EntityFactory interface,
 * responsible for creating and initializing instances of the {@link Entity} class in javafx environment.
 * It utilizes a mapping between fields of the entity and UI components represented
 * by {@link Node}) to populate the entity fields with corresponding values from the different implementations of {@code class Node}.
 */
public class GenericEntityFactory <T extends Entity> implements EntityFactory{
    private final T entity;
    private Map<Field, Node> fieldNodeMap;


    /**
     * Constructs a new GenericEntityFactory instance.
     *
     * @param entity The base entity object used as a 'generic' prototype for creating new entities it is treated as being empty.
     * @param fieldNodeMap A map associating entity fields with their corresponding graphical node representations. The map is expected to match
     */
    public GenericEntityFactory(T entity, Map<Field, Node> fieldNodeMap) {
        this.entity = entity;
        this.fieldNodeMap = fieldNodeMap;
    }

    @Override
    public Entity createEntity() {
        return createEntity(entity, fieldNodeMap);
    }

    /**
     * Creates and initializes an {@link Entity} object by mapping its fields to corresponding GUI nodes.
     * This method iterates through the fields of the given entity, maps each field based on its type
     * to the provided field-node mapping, and sets the corresponding values to the entity's fields.
     *
     * @param entity The base entity object to be initialized. Its fields will be populated based on the provided mapping.
     * @param fieldNodeMap A map associating entity fields with their corresponding GUI node representations.
     *                     The keys are the fields of the entity, and the values are GUI nodes like TextField, ComboBox, etc.
     * @return The updated {@link Entity} object with its fields populated based on the given field-node mapping.
     */
    private Entity createEntity(T entity, Map<Field, Node> fieldNodeMap) {
        Set<Field> entityFields = entity.toFieldNodesMap(new EntityTypeNodeMapper(entity.getClass())).keySet();

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

    /**
     * Sets the value of a specified field in an entity object based on the value taken
     * from a corresponding GUI node, such as a TextField, CheckBox, or DatePicker.
     * The value assignment depends on the field's type.
     *
     * @param field The field of the entity object whose value is to be set.
     * @param entity The entity object to which the field belongs.
     * @param node The GUI node corresponding to the field, used to retrieve the input value.
     * @param fieldType The data type of the field to ensure appropriate type handling and conversion.
     * @throws IllegalAccessException If the field cannot be accessed or modified.
     * @throws IllegalArgumentException If the field type is unsupported or the node is invalid.
     */
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