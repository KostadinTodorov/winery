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

public class EntityTypeNodeMapper implements EntityFieldMapper{
    private final Class<? extends Entity> entityClass;

    public EntityTypeNodeMapper(Class<? extends Entity> entityClass) {
        this.entityClass = entityClass;
    }

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

    protected Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
}