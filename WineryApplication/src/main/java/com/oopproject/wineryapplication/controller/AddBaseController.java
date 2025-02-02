package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.entities.creator.EntityFactory;
import com.oopproject.wineryapplication.access.entities.creator.GenericEntityFactory;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.access.entities.mappers.EntityTypeNodeMapper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Controller class responsible for managing the AddBase view and its functionality.
 * This class handles the creation, initialization, and handling of UI elements
 * for adding or editing an entity based on the provided {@code Entity} model.
 *
 * The {@code AddBaseController} connects the JavaFX UI with the underlying business logic
 * and entity model by mapping fields to UI nodes and managing user interactions.
 */
public class AddBaseController {
    @FXML
    public AnchorPane AddBase;
    @FXML
    public VBox entityProps;
    private Map<Field, Node> fieldNodeMap = new HashMap<>();
    private final Entity emptyEntity;
    private Entity entity;
    private ComboBox<String> comboBox;
    private Map<String, Entity> entityMap;

    public AddBaseController(Entity emptyEntity) {
        this.emptyEntity = emptyEntity;
    }

    /**
     * Initializes the controller by setting up UI components and their corresponding behaviors.
     *
     * This method is automatically invoked after the FXML file has been loaded. It adds a "Save" button
     * to the UI, maps entity fields to their corresponding nodes, and generates UI nodes dynamically.
     * The "Save" button is configured to trigger the `saveButton` private method when clicked, which
     * handles saving the entity and displaying appropriate alerts.
     *
     * Operations performed in this method include:
     * - Creating and adding a "Save" button to the UI layout.
     * - Setting an action handler for the "Save" button.
     * - Using the `toFieldNodesMap` method of the `emptyEntity` object to generate a field-to-node mapping.
     * - Dynamically generating UI nodes based on the field-to-node mapping using the `generateNodes` method.
     */
    @FXML
    public void initialize() {
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveButton());
        entityProps.getChildren().add(saveButton);
        fieldNodeMap = emptyEntity.toFieldNodesMap(new EntityTypeNodeMapper(emptyEntity.getClass()));
        generateNodes(fieldNodeMap);
    }

    /**
     * Dynamically generates UI nodes based on the provided mapping of fields to nodes.
     * Adds a label for each field (excluding fields of type Set or named "id") and the corresponding
     * node to the entity properties container.
     *
     * @param fieldNodeMap a map where keys represent fields of the entity and values represent
     *                     their corresponding UI nodes
     */
    private void generateNodes(Map<Field, Node> fieldNodeMap){
        for (Map.Entry<Field, Node> entry : fieldNodeMap.entrySet()) {
            if (!(entry.getKey().getType().equals(Set.class) || entry.getKey().getName().equals("id"))) {
                Label fieldLabel = new Label(entry.getKey().getName());
                entityProps.getChildren().addAll(fieldLabel, entry.getValue());
            }
        }
    }

//    private boolean isFilled() {
//        for (Map.Entry<Field, Node> entry : fieldNodedMap.entrySet()) {
//            if (entry.getValue() entry.getValue().getText().isEmpty()) {
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     * Handles the logic for saving the current entity to the database and providing user feedback.
     *
     * This method is invoked when a "Save" button is triggered in the UI. It performs the following steps:
     * - Calls the `setEntity` method to populate the entity based on user input.
     * - If `setEntity` fails, displays an alert informing users to correctly fill in required fields.
     * - If `setEntity` succeeds, calls the `saveEntity` method to attempt persistence of the entity in the database.
     * - If `saveEntity` succeeds, clears all input fields via `clearNodes` and displays a success alert.
     * - If `saveEntity` fails, displays an error alert indicating that the saving process was unsuccessful.
     *
     * Alerts inform users about the status of the save operation and provide guidance on how to proceed.
     */
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

    /**
     * Attempts to create an entity using a factory implementation and assign it to the `entity` field.
     *
     * This method uses a `GenericEntityFactory` initialized with an empty entity and a field-to-node
     * mapping to generate a populated entity. If the entity creation is successful, the `entity` field
     * is updated, and the method returns `true`. If an exception occurs during entity creation, the
     * method catches the exception and returns `false`.
     *
     * @return {@code true} if the entity has been successfully created and assigned; {@code false} otherwise.
     */
    private boolean setEntity() {
        EntityFactory genericEntityFactory = new GenericEntityFactory(emptyEntity,fieldNodeMap);
        try {
            entity = genericEntityFactory.createEntity();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Attempts to persist the current entity to the database.
     *
     * This method verifies if the entity is not null and then delegates the addition
     * operation to the DAO associated with the entity's type. If an exception
     * occurs during this process, the method handles it gracefully and returns {@code false}.
     *
     * @return {@code true} if the entity was successfully saved; {@code false} otherwise
     */
    private boolean saveEntity() {
        try{
            return entity != null? emptyEntity.getDao().add(entity) : false;
        } catch (Exception e){
            return false;
        }
    }

//    public boolean classImplementsInterface(Class<?> clazz, Class<?> interfaceClass) {
//        // Check if the class directly implements the interface
//        for (Class<?> implementedInterface : clazz.getInterfaces()) {
//            if (implementedInterface.equals(interfaceClass)) {
//                return true;
//            }
//        }
//
//        // Check if any of the superclasses implement the interface
//        Class<?> superclass = clazz.getSuperclass();
//        while (superclass != null) {
//            if (classImplementsInterface(superclass, interfaceClass)) {
//                return true;
//            }
//            superclass = superclass.getSuperclass();
//        }
//
//        return false;
//    }

    /**
     * Clears the values of all UI nodes associated with the field-to-node mapping.
     *
     * This method iterates through the nodes in the `fieldNodeMap` and resets their
     * values to their default state based on the node type. Specifically:
     * - For `TextField` nodes, their text is set to an empty string.
     * - For `ComboBox` nodes, the selection is cleared.
     * - For `DatePicker` nodes, their value is set to null.
     * - For `CheckBox` nodes, their selection state is set to unchecked (false).
     */
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
