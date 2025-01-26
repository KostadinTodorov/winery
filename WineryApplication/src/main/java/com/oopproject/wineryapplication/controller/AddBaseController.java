package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.entities.creator.EntityFactory;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.access.entities.helper.EntityFieldMap;
import com.oopproject.wineryapplication.access.entities.helper.EntityTypeNodeMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

public class AddBaseController {
    @FXML
    public AnchorPane AddBase;
    @FXML
    public VBox entityProps;
    private Map<Field, Node> fieldNodeMap = new HashMap<>();
    private final Entity entity;

    private ComboBox<String> comboBox;
    private Map<String, Entity> entityMap;

    public AddBaseController(Entity entity) {
        this.entity = entity;
    }

    @FXML
    public void initialize() {
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
            if (entity.getDao().add(entity)) {
                return true;
            }
        } catch (Exception e){
            return false;
        }
        return true;
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
