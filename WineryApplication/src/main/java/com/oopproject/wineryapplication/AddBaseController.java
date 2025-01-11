package com.oopproject.wineryapplication;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.access.entities.entity.EntityFieldMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        saveButton.setOnAction(event -> setEntity());
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

                // Assuming you have a method to retrieve a list of entities
                List<Entity> entities = new TemplateDao<Entity>(entityField).getAll();

                // Create an ObservableList from the list of entities
                ObservableList<Entity> observableEntities = FXCollections.observableArrayList(entities);

                // Set the items of the ComboBox
                entityComboBox.setItems(observableEntities);

                fieldNodedMap.put(field, entityComboBox);
                EntityProps.getChildren().add(label);
                EntityProps.getChildren().add(entityComboBox);
            } else if (!(fieldName.equals("id") && fieldType.equals(Set.class))) {
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

//    public ComboBoxWithEntityMap() {
//        entityMap = new HashMap<>();
//        // Example: Add some entities to the map
//        entityMap.put("entity1", new Employee());
//        entityMap.put("entity2", new Employee());
//        entityMap.put("entity3", new Employee());
//
//        // Create the ComboBox
//        comboBox = new ComboBox<>();
//        comboBox.setItems(FXCollections.observableArrayList(entityMap.keySet()));
//
//        // Handle selection changes
//        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                Entity selectedEntity = entityMap.get(newValue);
//                // Process the selected entity
//                if (selectedEntity instanceof Entity) {
//                    Entity entity = (Entity) selectedEntity;
//                    System.out.println("Selected Entity: " + entity.getName());
//                    // Perform actions with the selected entity
//                }
//            }
//        });
//    }

    private boolean isFilled() {
//        for (Map.Entry<Field, Node> entry : fieldNodedMap.entrySet()) {
//            if (entry.getValue() entry.getValue().getText().isEmpty()) {
//                return false;
//            }
//        }
        return true;
    }

    private void doshit(){
        boolean a = setEntity();
        Entity mrdog = entity;
    }

    private boolean setEntity() {
        if (isFilled()){
            if (!filled) {
                for (Map.Entry<Field, Node> entry : fieldNodedMap.entrySet()) {
                    Field field = entry.getKey();


                    field.setAccessible(true);

                    try {
                        Class<?> fieldType = field.getType();

                        if (fieldType == String.class) {
                            TextField textField = (TextField) entry.getValue();
                            field.set(entity, textField.getText());
                        } else if (fieldType == Integer.class) {
                            TextField textField = (TextField) entry.getValue();
                            field.set(entity, Integer.parseInt(textField.getText()));
                        } else if (fieldType == Double.class) {
                            TextField textField = (TextField) entry.getValue();
                            field.set(entity, Double.parseDouble(textField.getText()));
                        } else if (fieldType == Boolean.class) {
                            TextField textField = (TextField) entry.getValue();
                            field.set(entity, Boolean.parseBoolean(textField.getText()));
                        } else if (Entity.class.isAssignableFrom(fieldType)) {
                            ComboBox<Entity> entityComboBox = (ComboBox<Entity>) entry.getValue();
                            field.set(entity, entityComboBox.getValue());
                        }
                    } catch (IllegalAccessException e) {
                        return false;
                    }

                }
                return true;
            }
            return true;
        }
        return false;
    }
    public void saveEntity() {

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
}
