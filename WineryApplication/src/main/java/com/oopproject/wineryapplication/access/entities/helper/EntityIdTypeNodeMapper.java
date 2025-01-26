package com.oopproject.wineryapplication.access.entities.helper;

import com.oopproject.wineryapplication.access.entities.entity.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityIdTypeNodeMapper extends EntityTypeNodeMapper{
    public EntityIdTypeNodeMapper(Class<? extends Entity> entityClass) {
        super(entityClass);
    }

//    @Override
//    public Map<Field, Node> getFieldNodeMap(){
//        Map<Field, Node> fieldNodeMap = super.getFieldNodeMap();
//        // Iterate through the fieldNodeMap and replace the value where key.getName() equals "id"
//        ComboBox<Entity> entityComboBox = new ComboBox<>();
//        List<? extends Entity> entities = new TemplateDao<>(super.getEntityClass()).getAll();
//        ObservableList<Entity> observableEntities = FXCollections.observableArrayList(entities);
//        entityComboBox.setItems(observableEntities);
//        fieldNodeMap.replaceAll((key, value) -> key.getName().equals("id") ? entityComboBox : value);
//        return fieldNodeMap;
//    }
}