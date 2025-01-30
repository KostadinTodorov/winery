package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.helpers.LoggerHelper;
import com.oopproject.wineryapplication.helpers.LoggerLevels;
import com.oopproject.wineryapplication.helpers.Nodes;
import com.oopproject.wineryapplication.helpers.SceneHelper;
import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.entity.Entity;


import com.oopproject.wineryapplication.access.entities.creator.EntityCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;


import java.lang.reflect.Field;
import java.util.*;

public class DisplayBaseController<T extends Entity> {
    public class DeleteBase{

        private final int id;

        public DeleteBase(int id) {
            this.id = id;
        }

        public DeleteBase(Entity entity) {
            this.id = (int) entity.getId();
        }

        public boolean delete() {
            // Cast the entityClass to a TemplateDao<T> type
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
        LoggerHelper.logData(DisplayBaseController.class, LoggerLevels.INFO, "Initialize Display Base Controller");

        add.setOnAction(e -> {addEntity();});
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
        TableColumn<Entity, Void> deleteButtonColumn = new TableColumn<>("Delete"); // No data type needed

        deleteButtonColumn.setCellFactory(new Callback<TableColumn<Entity, Void>, TableCell<Entity, Void>>() {
            @Override
            public TableCell<Entity, Void> call(final TableColumn<Entity, Void> param) {
                final TableCell<Entity, Void> cell = new TableCell<Entity, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction(event -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Are you sure?");
                            alert.setHeaderText("Confirm deletion");
                            alert.setContentText("This cannot be reverted!");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                Entity entity = (Entity) this.getTableView().getItems().get(getIndex());
                                if(new DeleteBase(entity).delete()){
                                    alert1.setTitle("Item deleted successfully!");
                                    alert1.setHeaderText("Item deleted successfully!");
                                    alert1.setContentText("You have successfully deleted this item!");
                                    getTableView().getItems().remove(entity);
                                }
                                else {
                                    alert1.setTitle("Item wasn't deleted successfully!");
                                    alert1.setHeaderText("Item wasn't deleted successfully!");
                                    alert1.setContentText("Items cannot be deleted if they are referenced in other tables.");
                                }
                                alert1.showAndWait();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
        columns.add(deleteButtonColumn);

        TableColumn<Entity, Void> editButtonColumn = new TableColumn<>("Edit"); // No data type needed

        editButtonColumn.setCellFactory(new Callback<TableColumn<Entity, Void>, TableCell<Entity, Void>>() {
            @Override
            public TableCell<Entity, Void> call(final TableColumn<Entity, Void> param) {
                final TableCell<Entity, Void> cell = new TableCell<Entity, Void>() {

                    private final Button btn = new Button("Edit");

                    {
//                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                        alert.setTitle("Are you sure?");
//                        alert.setHeaderText("Confirm deletion");
//                        alert.setContentText("This cannot be reverted!");
//                        Optional<ButtonType> result = alert.showAndWait();
//                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            btn.setOnAction(event -> {
                                Entity entity = (Entity) this.getTableView().getItems().get(getIndex());
                                editEntity(entity);
                            });
//                        }
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
        columns.add(editButtonColumn);

        entityTableView.getColumns().addAll(columns);

        ObservableList<Entity> entities = FXCollections.observableArrayList(entityList);
        entityTableView.setItems(entities);
    }

    private void addEntity() {
        T entity = EntityCreator.createInstance(entityClass);

        LoggerHelper.logData(DisplayBaseController.class, LoggerLevels.INFO, "Call Add Base Controller");
        SceneHelper.<AddBaseController>addNode(DisplayBase, Nodes.ADDBASE, new AddBaseController(entity));
    }

    private void editEntity(Entity entity) {
        LoggerHelper.logData(DisplayBaseController.class, LoggerLevels.INFO, "Call Edit Base Controller");
        SceneHelper.<EditBaseController>addNode(DisplayBase, Nodes.EDITBASE, new EditBaseController(entity));
    }
}