package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.entity.Entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Field;
import java.util.*;

public class DisplayBaseController {

    @FXML
    public AnchorPane DisplayBase;
    @FXML
    public TableView<Entity> entityTableView;
    private final List<Entity> entityList;
    private final Class<Entity> entityClass;

    public DisplayBaseController(Class<?> entityClass) {
        this.entityClass = (Class<Entity>) entityClass;
        this.entityList = new TemplateDao<>((Class<Entity>) entityClass).getAll();
    }

    @FXML
    public void initialize() {
        List<TableColumn<Entity, ?>> columns = new ArrayList<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();


//            EntityFieldMap entityFieldMap = new EntityFieldMap(entityClass);
            try {
//                if (Entity.class.isAssignableFrom(fieldType)) {
//                    Class<Entity> entityField = (Class<Entity>) fieldType;
//                    TableColumn<Entity, EntityDisplayWrapper> column = new TableColumn<>(fieldName);
//                    EntityDisplayWrapper entityDisplayWrapper = new EntityDisplayWrapper(field.get());
//                    columns.add(column);
//                } else {
//                    TableColumn<Entity, ?> column = new TableColumn<>(fieldName);
//                    column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
//                    columns.add(column);
//                }
                TableColumn<Entity, ?> column = new TableColumn<>(fieldName);
                column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                columns.add(column);
            } catch (Exception e) {
            }


        }

        entityTableView.getColumns().addAll(columns);

        ObservableList<Entity> entities = FXCollections.observableArrayList(entityList);
        entityTableView.setItems(entities);
    }


}