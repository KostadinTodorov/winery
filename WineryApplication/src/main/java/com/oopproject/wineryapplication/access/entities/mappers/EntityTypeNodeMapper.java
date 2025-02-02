package com.oopproject.wineryapplication.access.entities.mappers;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a concrete implementation of the {@link EntityFieldMapper} interface.
 * This class provides functionality to generate a map that associates fields of a specific
 * {@link Entity} class with corresponding JavaFX UI nodes.
 *
 * This class is used to dynamically map entity fields to appropriate UI components, enabling automatic
 * generation of user interfaces for managing entities.
 */
public class EntityTypeNodeMapper implements EntityFieldMapper{
    private final Class<? extends Entity> entityClass;

    /**
     * Constructor for the EntityTypeNodeMapper class.
     * Creates a new instance that maps fields of the specified {@code entityClass}
     * to corresponding JavaFX UI nodes.
     *
     * @param entityClass the class type of the {@code Entity} to be used for field-to-node mapping
     */
    public EntityTypeNodeMapper(Class<? extends Entity> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Generates and returns a mapping between the declared fields of the entity class
     * and their corresponding JavaFX UI nodes. The method dynamically assigns appropriate
     * UI components based on the type of each field.
     *
     * Fields of type {@link Entity} are associated with a {@link ComboBox} populated with
     * all instances of the entity type. Boolean fields are mapped to a {@link CheckBox},
     * {@link LocalDate} fields are assigned a {@link DatePicker}, and other field types are
     * linked to a {@link TextField} with a prompt indicating the field's type.
     *
     * @return a map where keys are {@link Field} objects representing the fields of the
     *         entity class, and values are corresponding {@link Node} objects for the UI.
     */
    public Map<Field, Node> getFieldNodeMap() {
        Map<Field, Node> fieldNodeMap = new HashMap<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            if (Entity.class.isAssignableFrom(fieldType)) {
                Class<Entity> entityField = (Class<Entity>) fieldType;
                ComboBox<Entity> entityComboBox = new ComboBox<>();
                List<Entity> entities = new TemplateDao<Entity>(entityField).getAll();
                ObservableList<Entity> observableEntities = FXCollections.observableArrayList(entities);
                entityComboBox.setItems(observableEntities);
                fieldNodeMap.put(field, entityComboBox);
            } else if (fieldType.equals(Boolean.class)) {
                CheckBox checkBox = new CheckBox();
                checkBox.setId(fieldName);
                fieldNodeMap.put(field, checkBox);
            } else if (fieldType.equals(LocalDate.class)) {
                DatePicker datePicker = new DatePicker();
                datePicker.setId(fieldName);
                fieldNodeMap.put(field, datePicker);
            } else {
                TextField textField = new TextField();
                textField.setPromptText(fieldType.getTypeName());
                textField.setId(fieldName);
                fieldNodeMap.put(field, textField);
            }
        }
        return fieldNodeMap;
    }

    /**
     * Retrieves the class type of the entity associated with this mapper.
     *
     * This method provides access to the entity class that is used for creating
     * field-to-node mappings or other operations requiring knowledge of the
     * entity's type. The returned class type is determined during the
     * instantiation of the containing class.
     *
     * @return the class type of the associated entity, extending {@code Entity}.
     */
    protected Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
}