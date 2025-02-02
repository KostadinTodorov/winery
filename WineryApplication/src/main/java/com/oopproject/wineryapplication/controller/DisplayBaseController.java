package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.helpers.Nodes;
import com.oopproject.wineryapplication.helpers.SceneHelper;
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

public class DisplayBaseController<T extends Entity> {

    public class DeleteBase {
        private final int id;

        public DeleteBase(int id) {
            this.id = id;
        }

        public DeleteBase(Entity entity) {
            this.id = (int) entity.getId();
        }

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

    @FXML
    public void initialize() {
        initializeAddButton();
        initializeTableView();
    }

    private void initializeAddButton() {
        add.setOnAction(e -> addEntity());
    }

    private void initializeTableView() {
        List<TableColumn<Entity, ?>> columns = createColumns();
        addDeleteButtonColumn(columns);
        addEditButtonColumn(columns);

        entityTableView.getColumns().addAll(columns);

        ObservableList<Entity> entities = FXCollections.observableArrayList(entityList);
        entityTableView.setItems(entities);
    }

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

    private void addDeleteButtonColumn(List<TableColumn<Entity, ?>> columns) {
        TableColumn<Entity, Void> deleteButtonColumn = new TableColumn<>("Delete");
        deleteButtonColumn.setCellFactory(createDeleteButtonCellFactory());
        columns.add(deleteButtonColumn);
    }

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

    private boolean showDeleteConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("Confirm deletion");
        alert.setContentText("This cannot be reverted!");
        return alert.showAndWait().filter(result -> result == ButtonType.OK).isPresent();
    }

    private boolean deleteEntity(Entity entity) {
        return new DeleteBase(entity).delete();
    }

    private void showDeleteSuccessAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Item deleted successfully!", "You have successfully deleted this item!");
    }

    private void showDeleteFailureAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Item wasn't deleted successfully!", "Items cannot be deleted if they are referenced in other tables.");
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void addEditButtonColumn(List<TableColumn<Entity, ?>> columns) {
        TableColumn<Entity, Void> editButtonColumn = new TableColumn<>("Edit");
        editButtonColumn.setCellFactory(createEditButtonCellFactory());
        columns.add(editButtonColumn);
    }

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


    private void addEntity() {
        try {
            Constructor<T> constructor = entityClass.getDeclaredConstructor();
            T entity = constructor.newInstance();
            SceneHelper.<AddBaseController>addNode(DisplayBase, Nodes.ADDBASE, new AddBaseController(entity));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + entityClass.getSimpleName(), e);
        }
    }

    private void editEntity(Entity entity) {
        SceneHelper.<EditBaseController>addNode(DisplayBase, Nodes.EDITBASE, new EditBaseController(entity));
    }
}