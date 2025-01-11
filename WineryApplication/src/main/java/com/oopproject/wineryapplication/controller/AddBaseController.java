package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.access.entities.entity.EntityFieldMap;
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
    public VBox EntityProps;
    private final EntityFieldMap entityFieldMap;
    private boolean filled = false;
    private Map<Field, Node> fieldNodedMap = new HashMap<>();
    private Class<?> entityClass;
    private Entity entity;

    private ComboBox<String> comboBox;
    private Map<String, Entity> entityMap;

    public AddBaseController(Entity entity) {
        entityFieldMap = new EntityFieldMap(entity.getClass());
        entityClass = entity.getClass();
        this.entity = entity;
    }

    @FXML
    public void initialize() {
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveButton());
        EntityProps.getChildren().add(saveButton);
        Field[] fields = entityFieldMap.getFields();
        int i = 0;
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            javafx.scene.control.Label label = new javafx.scene.control.Label(fieldName);
            if (classImplementsInterface(fieldType,Entity.class)) {
                Class<Entity> entityField = (Class<Entity>) fieldType;
                ComboBox<Entity> entityComboBox = new ComboBox<>();
                List<Entity> entities = new TemplateDao<Entity>(entityField).getAll();
                ObservableList<Entity> observableEntities = FXCollections.observableArrayList(entities);
                entityComboBox.setItems(observableEntities);
                fieldNodedMap.put(field, entityComboBox);
                EntityProps.getChildren().add(label);
                EntityProps.getChildren().add(entityComboBox);
            } else if (fieldType.equals(Boolean.class)) {
                CheckBox checkBox = new CheckBox();
                checkBox.setId(fieldName);
                EntityProps.getChildren().add(label);
                fieldNodedMap.put(field, checkBox);
                EntityProps.getChildren().add(checkBox);
            } else if (fieldType.equals(LocalDate.class)) {
                DatePicker datePicker = new DatePicker();
                datePicker.setId(fieldName);
                EntityProps.getChildren().add(label);
                fieldNodedMap.put(field, datePicker);
                EntityProps.getChildren().add(datePicker);
            } else if (!(fieldName.equals("id") || fieldType.equals(Set.class))) {
                TextField textField = new TextField();

                textField.setPromptText(fieldType.getTypeName());
                textField.setId(fieldName);
                fieldNodedMap.put(field, textField);
                i++;
                EntityProps.getChildren().add(label);
                EntityProps.getChildren().add(textField);
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
        if (isFilled()){
            if (!filled) {
                for (Map.Entry<Field, Node> entry : fieldNodedMap.entrySet()) {
                    Field field = entry.getKey();


                    field.setAccessible(true);

                    try {
                        Class<?> fieldType = field.getType();
                        if (Entity.class.isAssignableFrom(fieldType)) {
                            ComboBox<Entity> entityComboBox = (ComboBox<Entity>) entry.getValue();
                            field.set(entity, entityComboBox.getValue());
                        } else {
                            TextField textField;
                            switch (fieldType.getSimpleName()) {
                                case "String":
                                    textField = (TextField) entry.getValue();
                                    field.set(entity, textField.getText());
                                    break;
                                case "Short":
                                    textField = (TextField) entry.getValue();
                                    field.set(entity, Short.parseShort(textField.getText()));
                                    break;
                                case "Integer":
                                    textField = (TextField) entry.getValue();
                                    field.set(entity, Integer.parseInt(textField.getText()));
                                    break;
                                case "Long":
                                    textField = (TextField) entry.getValue();
                                    field.set(entity, Long.parseLong(textField.getText()));
                                    break;
                                case "Float":
                                    textField = (TextField) entry.getValue();
                                    field.set(entity, Float.parseFloat(textField.getText()));
                                    break;
                                case "Double":
                                    textField = (TextField) entry.getValue();
                                    field.set(entity, Double.parseDouble(textField.getText()));
                                    break;
                                case "Boolean":
                                    CheckBox checkBox = (CheckBox) entry.getValue();
                                    field.set(entity, checkBox.isSelected());
                                    break;
                                case "LocalDate":
                                    DatePicker datePicker = new DatePicker();
                                    field.set(entity, datePicker.getValue());
                                    break;
                                default:
                                    // Handle unexpected Number subclasses
                                    System.err.println("Unexpected Number subclass: " + fieldType.getName());
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        }
        return false;
    }
    public boolean saveEntity() {
        try{
            var t = new TemplateDao<>(Entity.class);
            if (!t.add(entity)) {
                return false;
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
        for (var node : fieldNodedMap.values()) {
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
