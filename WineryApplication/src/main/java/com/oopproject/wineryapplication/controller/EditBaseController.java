package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.creator.EntityFactory;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.access.entities.helper.EntityIdTypeNodeMapper;
import com.oopproject.wineryapplication.access.entities.helper.EntityTypeNodeMapper;
import com.oopproject.wineryapplication.helpers.LoggerHelper;
import com.oopproject.wineryapplication.helpers.LoggerLevels;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EditBaseController {
    @FXML
    public AnchorPane EditBase;
    @FXML
    public VBox entityProps;
    private Map<Field, Node> fieldNodeMap = new HashMap<>();
    private final Entity entity;

    private ComboBox<String> comboBox;
    private Map<String, Entity> entityMap;

    public EditBaseController(Entity entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.entity = entity;
    }

    @FXML
    public void initialize() {
        LoggerHelper.logData(EditBaseController.class, LoggerLevels.INFO, "Initialize Edit Base Controller");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveButton());
        entityProps.getChildren().add(saveButton);
        fieldNodeMap = entity.toNode(new EntityTypeNodeMapper(entity.getClass()));
        generateNodes(fieldNodeMap);
    }

    private void generateNodes(Map<Field, Node> fieldNodeMap){
        for (Map.Entry<Field, Node> entry : fieldNodeMap.entrySet()) {
            if (!(entry.getKey().getType().equals(Set.class) || entry.getKey().getName().equals("id"))) {
                Label fieldLabel = new Label(entry.getKey().getName());
                entityProps.getChildren().addAll(fieldLabel, entry.getValue());
            }
        }

        LoggerHelper.logData(EditBaseController.class, LoggerLevels.INFO, "Generate buttons and fields for Edit Base Controller");
    }

    private boolean isFilled() {
//        for (Map.Entry<Field, Node> entry : fieldNodedMap.entrySet()) {
//            if (entry.getValue() entry.getValue().getText().isEmpty()) {
//                return false;
//            }
//        }
        return true;
    }

    private void saveButton(){
        if (setEntity()) {
            if (saveEntity()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                clearNodes();
                alert.setTitle("Information saved to the database");
                alert.setContentText("You have successfully saved to database!");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information wasn't saved to the database");
                alert.setContentText("Something went wrong!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fields not filled correctly");
            alert.setContentText("Make sure you are using the correct format for each field and that you have filled the required fields!");
            alert.showAndWait();
        }
    }

    private boolean setEntity() {
        EntityFactory entityFactory = new EntityFactory();
        try {
            entityFactory.createEntity(entity,fieldNodeMap);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean saveEntity() {
        try{
//            if (entity.getDao().update(entity.getId(),entity)) {
//                return true;
//            }
            TemplateDao<Entity> dao = new TemplateDao<>((Class<Entity>) entity.getClass());
            return dao.update(entity.getId(), entity);
        } catch (Exception e){
            return false;
        }
    }

    public boolean classImplementsInterface(Class<?> clazz, Class<?> interfaceClass) {
        // Check if the class directly implements the interface
        for (Class<?> implementedInterface : clazz.getInterfaces()) {
            if (implementedInterface.equals(interfaceClass)) {
                return true;
            }
        }

        // Check if any of the superclasses implement the interface
        Class<?> superclass = clazz.getSuperclass();
        while (superclass != null) {
            if (classImplementsInterface(superclass, interfaceClass)) {
                return true;
            }
            superclass = superclass.getSuperclass();
        }

        return false;
    }

    private void clearNodes() {
        for (var node : fieldNodeMap.values()) {
            if (node instanceof TextField) {
                ((TextField) node).setText("");
            } else if (node instanceof ComboBox) {
                ((ComboBox<?>) node).getSelectionModel().clearSelection();
            } else if (node instanceof DatePicker) {
                ((DatePicker) node).setValue(null);
            } else if(node instanceof CheckBox) {
                ((CheckBox) node).setSelected(false);
            }
        }
    }
}
