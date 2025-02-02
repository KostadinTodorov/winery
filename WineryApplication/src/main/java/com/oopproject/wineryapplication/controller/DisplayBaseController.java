package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.helpers.scenes.Nodes;
import com.oopproject.wineryapplication.helpers.scenes.SceneHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The DisplayBaseController class is a generic controller designed to manage
 * and display a collection of entities within a JavaFX application. It provides
 * functionality for viewing, adding, editing, and deleting entities from a table view.
 *
 * @param <T> the type of entity being managed by this controller, which extends the Entity class.
 */
public class DisplayBaseController<T extends Entity> {

    /**
     * The DeleteBase class provides functionality for deleting entities in the system.
     * It uses the {@link TemplateDao} to perform delete operations on an entity
     * identified by its unique ID.
     *
     * This class offers multiple constructors for flexibility:
     * 1. Accepting an integer ID directly.
     * 2. Accepting an {@link Entity} object, from which the ID is derived.
     *
     * The `delete` method is the primary operation of this class, executing
     * the deletion of an entity through the data access object.
     */
    public class DeleteBase {
        private final int id;

        public DeleteBase(int id) {
            this.id = id;
        }

        public DeleteBase(Entity entity) {
            this.id = (int) entity.getId();
        }

        /**
         * Deletes an entity identified by its unique ID using the {@link TemplateDao}.
         *
         * This method leverages the data access object to remove the entity of the specified
         * type from the underlying database or data storage. If the entity with the given ID
         * exists, it is removed, and the operation is considered successful.
         *
         * @return true if the entity is successfully deleted, false otherwise (e.g., if the entity does not exist or an exception occurs).
         */
        public boolean delete() {
            TemplateDao<?> dao = new TemplateDao<>(entityClass);
            return dao.delete(id);
        }
    }

    @FXML
    public AnchorPane DisplayBase;
    @FXML
    public TableView<Entity> entityTableView;
    @FXML
    public Button add;

    private final List<T> entityList;
    private final Class<T> entityClass;

    public DisplayBaseController(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityList = new TemplateDao<T>(entityClass).getAll();
    }

    /**
     * Initializes the controller's components and sets up the necessary configurations
     * for the associated view. This method is called automatically after the
     * FXML file has been loaded and the associated components are injected.
     *
     * The method performs the following actions:
     * - Calls {@link #initializeAddButton()} to configure the behavior of the "Add" button.
     * - Calls {@link #initializeTableView()} to set up the table view, including column creation
     *   and the addition of edit and delete button functionalities.
     */
    @FXML
    public void initialize() {
        initializeAddButton();
        initializeTableView();
    }

    /**
     * Configures the "Add" button to handle user interactions.
     *
     * This method sets the action event for the "Add" button, enabling it to trigger
     * the {@link #addEntity()} method when clicked. The primary purpose of this
     * functionality is to create and display a new instance of the entity type
     * managed by the controller.
     *
     * The {@link #addEntity()} method is responsible for instantiating a new
     * entity object using reflection and transitioning to a specific scene that
     * handles its addition.
     */
    private void initializeAddButton() {
        add.setOnAction(e -> addEntity());
    }

    /**
     * Sets up and initializes the table view for displaying entity data.
     *
     * This method performs the following actions:
     * 1. Calls the {@link #createColumns()} method to dynamically create table columns
     *    based on the fields of the entity class.
     * 2. Invokes {@link #addDeleteButtonColumn(List)} to add a delete button column functionality.
     * 3. Invokes {@link #addEditButtonColumn(List)} to add an edit button column functionality.
     * 4. Adds all the created and customized columns to the `entityTableView`.
     * 5. Initializes the table's items with an observable list of the existing entities.
     *
     * The method ensures that the table view is configured with appropriate attributes,
     * column definitions, and data bindings for displaying and interacting with entity data.
     */
    private void initializeTableView() {
        List<TableColumn<Entity, ?>> columns = createColumns();
        addDeleteButtonColumn(columns);
        addEditButtonColumn(columns);

        entityTableView.getColumns().addAll(columns);

        ObservableList<Entity> entities = FXCollections.observableArrayList(entityList);
        entityTableView.setItems(entities);
    }

    /**
     * Creates and returns a list of table columns dynamically generated based on the fields
     * of the `Entity` class associated with this controller.
     *
     * This method uses reflection to retrieve declared fields of the entity class.
     * For each field, a corresponding `TableColumn` instance is created, and its
     * cell value factory is configured to bind to the property with the name of the field.
     * The generated columns are added to a list, which is then returned.
     *
     * Any exceptions that occur while creating the columns are caught and handled
     * within the method, typically by logging the error or printing the stack trace.
     *
     * @return a list of `TableColumn` instances corresponding to the fields of the `Entity` class
     */
    private List<TableColumn<Entity, ?>> createColumns() {
        List<TableColumn<Entity, ?>> columns = new ArrayList<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            try {
                TableColumn<Entity, ?> column = new TableColumn<>(fieldName);
                column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                columns.add(column);
            } catch (Exception e) {
                // Handle or log the exception as needed
                e.printStackTrace(); // Example: print stack trace for debugging
            }
        }
        return columns;
    }

    /**
     * Adds a "Delete" button column to the provided list of table columns.
     * Each row in the table column will include a button that, when clicked,
     * triggers the deletion of the corresponding entity from the table and data source.
     *
     * The column header is labeled "Delete". In each cell of this column, a "Delete"
     * button is rendered. Clicking the button will invoke the logic for confirming
     * the deletion and removing the associated entity.
     *
     * This method relies on {@link #createDeleteButtonCellFactory()} to provide the
     * cell factory that defines the behavior of the "Delete" button. The deletion
     * process includes showing a confirmation dialog, attempting the delete operation,
     * and showing a success or failure message.
     *
     * @param columns the list of {@code TableColumn} objects to which the "Delete"
     *                button column will be added
     */
    private void addDeleteButtonColumn(List<TableColumn<Entity, ?>> columns) {
        TableColumn<Entity, Void> deleteButtonColumn = new TableColumn<>("Delete");
        deleteButtonColumn.setCellFactory(createDeleteButtonCellFactory());
        columns.add(deleteButtonColumn);
    }

    /**
     * Creates and returns a cell factory for a table column that includes a "Delete" button in each row.
     *
     * The "Delete" button, when clicked, triggers the following sequence:
     * 1. Displays a confirmation dialog using {@code showDeleteConfirmation()}.
     * 2. If confirmed, attempts to delete the corresponding entity using {@code deleteEntity(Entity entity)}.
     * 3. If the deletion is successful, removes the entity from the table view and displays a success alert using {@code showDeleteSuccessAlert()}.
     * 4. If the deletion fails, displays a failure alert using {@code showDeleteFailureAlert()}.
     *
     * The returned cell factory is designed to be set on a table column, rendering a button in each cell
     * that provides functionality for the deletion of the associated entity.
     *
     * @return a callback that generates {@code TableCell} instances containing a "Delete" button with the outlined functionality
     */
    private Callback<TableColumn<Entity, Void>, TableCell<Entity, Void>> createDeleteButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<Entity, Void> call(final TableColumn<Entity, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction(event -> {
                            Entity entity = getTableView().getItems().get(getIndex());
                            if (showDeleteConfirmation()) {
                                if (deleteEntity(entity)) {
                                    showDeleteSuccessAlert();
                                    getTableView().getItems().remove(entity);
                                } else {
                                    showDeleteFailureAlert();
                                }
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : btn);
                    }
                };
            }
        };
    }

    /**
     * Displays a confirmation dialog asking the user to confirm a delete action.
     *
     * The dialog includes a title, a header text, and a content text that warns
     * the user the action cannot be reverted. The method waits for the user's
     * response and determines whether the delete action has been confirmed.
     *
     * @return true if the user confirms the deletion by clicking "OK", false otherwise
     */
    private boolean showDeleteConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("Confirm deletion");
        alert.setContentText("This cannot be reverted!");
        return alert.showAndWait().filter(result -> result == ButtonType.OK).isPresent();
    }

    /**
     * Deletes the specified entity from the system.
     *
     * This method utilizes the functionality of the {@code DeleteBase} class to
     * perform the deletion of the provided {@code Entity} instance. The deletion
     * is attempted, and the result indicates whether the operation was successful.
     *
     * @param entity the {@code Entity} instance to be deleted
     * @return {@code true} if the entity was successfully deleted;
     *         {@code false} otherwise
     */
    private boolean deleteEntity(Entity entity) {
        return new DeleteBase(entity).delete();
    }

    /**
     * Displays an informational alert to the user indicating that an item
     * was successfully deleted.
     *
     * The alert includes a message with the title "Item deleted successfully!"
     * and a header text that provides confirmation of the successful action.
     * The method uses the {@code showAlert} utility to create and show the alert
     * dialog with the specified parameters.
     *
     * This method can be utilized as part of a deletion workflow to provide
     * user feedback after a successful deletion process.
     */
    private void showDeleteSuccessAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Item deleted successfully!", "You have successfully deleted this item!");
    }

    /**
     * Displays a failure alert to inform the user that an item could not be deleted.
     *
     * This method is used when an attempt to delete an item fails due to external
     * constraints, such as the item being referenced in other tables. It shows
     * an alert of type {@link Alert.AlertType#INFORMATION}, providing the user with
     * a title and a detailed message explaining why the deletion was unsuccessful.
     *
     * The alert contains the following:
     * - Title: "Item wasn't deleted successfully!"
     * - Content: "Items cannot be deleted if they are referenced in other tables."
     *
     * This method enhances the usability of the system by ensuring feedback is given
     * when a delete operation does not proceed as expected.
     */
    private void showDeleteFailureAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Item wasn't deleted successfully!", "Items cannot be deleted if they are referenced in other tables.");
    }

    /**
     * Creates and displays an alert dialog with the specified type, title, and content.
     * The alert is configured to have a title, header text, and content text based on the provided parameters.
     * After displaying the alert, the method waits for the user to close it.
     *
     * @param type   the {@code Alert.AlertType} specifying the type of alert (e.g., INFORMATION, WARNING, ERROR, etc.)
     * @param title  the title of the alert dialog
     * @param content the content text to display in the alert dialog
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    /**
     * Adds an "Edit" button column to the provided list of table columns.
     *
     * The column header is labeled "Edit". In each cell of this column,
     * an "Edit" button is rendered. Clicking the button will invoke the
     * functionality provided by the associated cell factory to handle
     * editing actions for the corresponding entity.
     *
     * This method relies on the {@code createEditButtonCellFactory()}
     * method to provide the cell factory that defines the behavior of
     * the "Edit" button.
     *
     * @param columns the list of {@code TableColumn} objects to which the "Edit"
     *                button column will be added
     */
    private void addEditButtonColumn(List<TableColumn<Entity, ?>> columns) {
        TableColumn<Entity, Void> editButtonColumn = new TableColumn<>("Edit");
        editButtonColumn.setCellFactory(createEditButtonCellFactory());
        columns.add(editButtonColumn);
    }

    /**
     * Creates a cell factory for a TableColumn that generates cells containing an "Edit" button.
     * When the button is clicked, the associated entity is passed to the editEntity method.
     *
     * @return A Callback that provides TableCell instances, each containing an "Edit" button, for the TableColumn.
     */
    private Callback<TableColumn<Entity, Void>, TableCell<Entity, Void>> createEditButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<Entity, Void> call(final TableColumn<Entity, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction(event -> {
                            Entity entity = getTableView().getItems().get(getIndex());
                            editEntity(entity);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : btn);
                    }
                };
            }
        };
    }


    /**
     * Creates a new instance of the entity managed by this controller and initializes a scene
     * for adding it to the system.
     *
     * This method performs the following actions:
     * 1. Uses reflection to obtain the no-argument constructor of the entity class.
     * 2. Instantiates a new entity object using the retrieved constructor.
     * 3. Initializes and displays the "Add" scene by invoking the {@code SceneHelper.addNode} method,
     *    passing the controller for the "Add" scene along with the created entity.
     *
     * If an exception occurs during the instantiation or scene rendering process, the method
     * throws a runtime exception, providing specific details of the failure.
     *
     * Exceptions that may be handled include:
     * - {@code NoSuchMethodException}: If the entity class lacks a no-argument constructor.
     * - {@code InstantiationException}: If the entity class is abstract or cannot be instantiated.
     * - {@code IllegalAccessException}: If the constructor is inaccessible.
     * - {@code InvocationTargetException}: If the constructor throws an exception during instantiation.
     *
     * This method is primarily triggered by the "Add" button within the controller's user interface.
     * It facilitates dynamically handling the creation of new entities based on the associated entity class.
     */
    private void addEntity() {
        try {
            Constructor<T> constructor = entityClass.getDeclaredConstructor();
            T entity = constructor.newInstance();
            SceneHelper.<AddBaseController>addNode(DisplayBase, Nodes.ADDBASE, new AddBaseController(entity));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + entityClass.getSimpleName(), e);
        }
    }

    /**
     * Edits the given entity by creating and adding an EditBaseController
     * to the display base with the specified entity.
     *
     * @param entity the entity to be edited
     */
    private void editEntity(Entity entity) {
        SceneHelper.<EditBaseController>addNode(DisplayBase, Nodes.EDITBASE, new EditBaseController(entity));
    }
}