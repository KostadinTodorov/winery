package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The {@code EditBaseController} class provides a controller for editing and managing
 * the properties of an {@code Entity} in a JavaFX-based UI. This controller dynamically
 * generates forms based on the fields of the entity and supports saving the entity's
 * data back to a database or persistence layer.
 */
public class EditBaseController {
    @FXML
    public AnchorPane EditBase;
    @FXML
    public VBox entityProps;
    private Map<Field, Node> fieldNodeMap = new HashMap<>();
    private final Entity entity;

    private ComboBox<String> comboBox;
    private Map<String, Entity> entityMap;

    /**
     * Constructs an instance of EditBaseController with the specified entity.
     * This constructor ensures that the provided entity has a valid non-null ID.
     *
     * @param entity the entity to be managed by the controller. The entity must have a non-null ID.
     * @throws IllegalArgumentException if the entity's ID is null.
     */
    public EditBaseController(Entity entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.entity = entity;
    }

    /**
     * Initializes the JavaFX controller and sets up the user interface.
     *
     * This method is called automatically after the FXML file has been loaded. It performs the
     * following tasks:
     * 1. Creates a "Save" button and assigns an action event to it. On clicking the button,
     *    the {@code saveButton()} method is invoked to handle saving functionality.
     * 2. Adds the "Save" button to the {@code entityProps} layout container for display in the UI.
     * 3. Calls the {@code toFieldNodesMap} method on the associated {@code entity} object with an
     *    {@code EntityTypeNodeMapper} to dynamically generate a map of fields and corresponding UI nodes.
     * 4. Passes the field-node mapping to the {@code generateNodes()} method, which creates UI elements
     *    and adds them to the layout container.
     */
    @FXML
    public void initialize() {
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveButton());
        entityProps.getChildren().add(saveButton);
        fieldNodeMap = entity.toFieldNodesMap(new EntityTypeNodeMapper(entity.getClass()));
        generateNodes(fieldNodeMap);
    }

    /**
     * Generates and adds UI nodes to a container based on the provided field-to-node map.
     *
     * This method iterates over the provided map of {@code Field} to {@code Node},
     * and for each entry, it creates a label with the field name and adds the
     * corresponding node if the field type is not {@code Set} and the field name is not "id".
     *
     * @param fieldNodeMap a map where keys are {@code Field} objects representing class fields,
     *                     and values are {@code Node} objects representing the corresponding
     *                     JavaFX UI nodes to be added.
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
     * Handles the action of the "Save" button in the user interface.
     *
     * This method performs the following steps:
     * 1. Attempts to set the entity using the {@code setEntity()} method.
     *    If the entity cannot be set due to incorrect or incomplete input,
     *    an alert dialog is displayed, informing the user that fields are not
     *    filled correctly.
     * 2. If the entity is successfully set, the method then attempts to save
     *    the entity to the database using the {@code saveEntity()} method.
     *    - If the save operation is successful, a confirmation alert is shown
     *      to notify the user of the success, and the fields are cleared
     *      using the {@code clearNodes()} method.
     *    - If the save operation fails, an error alert is displayed, indicating
     *      that the save operation encountered an issue.
     * 3. Alerts are displayed dynamically based on the outcome of each step
     *    to guide the user's actions and provide feedback.
     *
     * The method ensures user interactions are responded to with appropriate
     * feedback and updates the user interface accordingly.
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
     * Initializes and sets an entity using the GenericEntityFactory.
     * The method creates a new entity instance by delegating to the factory
     * and handles any exceptions that may occur during the creation process.
     *
     * @return {@code true} if the entity is successfully created and initialized;
     *         {@code false} otherwise.
     */
    private boolean setEntity() {
        EntityFactory genericEntityFactory = new GenericEntityFactory(entity,fieldNodeMap);
        try {
            genericEntityFactory.createEntity();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Saves the current entity to the data source by delegating the operation
     * to the entity's data access object (DAO).
     *
     * This method first retrieves the DAO instance associated with the entity type,
     * and then attempts to update the entity using its unique identifier and current state.
     * In case of any exceptions during the save operation, the method returns {@code false}.
     *
     * @return {@code true} if the entity was successfully saved; {@code false} otherwise
     */
    public boolean saveEntity() {
        try{
            return entity.getDao().update(entity.getId(),entity);
//            TemplateDao<Entity> dao = new TemplateDao<>((Class<Entity>) entity.getClass());
//            return dao.update(entity.getId(), entity);
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Checks if a given class implements a specified interface either directly or through its superclasses.
     *
     * This method iterates over the interfaces implemented by the class and its superclasses
     * to determine whether the specified interface is implemented.
     *
     * @param clazz the class to check for interface implementation. Must not be null.
     * @param interfaceClass the interface to verify implementation of. Must not be null.
     * @return {@code true} if the class or any of its superclasses implement the specified interface;
     *         {@code false} otherwise.
     */
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

    /**
     * Clears the content or selection of UI nodes contained within the fieldNodeMap.
     *
     * This method iterates through the values of the fieldNodeMap and resets each node
     * to its default state based on its type:
     * - For TextField nodes, the text content is cleared.
     * - For ComboBox nodes, the current selection is cleared.
     * - For DatePicker nodes, the value is set to null.
     * - For CheckBox nodes, the selection is set to false.
     *
     * The method ensures that all UI elements associated with the map are returned to their
     * initial state for reuse or reinitialization.
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
