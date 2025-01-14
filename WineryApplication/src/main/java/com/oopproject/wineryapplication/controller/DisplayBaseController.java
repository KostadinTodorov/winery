package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.Nodes;
import com.oopproject.wineryapplication.SceneHelper;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.entities.entity.Entity;

import com.oopproject.wineryapplication.access.entities.entity.EntityFieldMap;
import com.oopproject.wineryapplication.data.EntityCreator;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


import java.lang.reflect.Field;
import java.time.LocalDate;
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
        TableColumn<Entity, Void> buttonColumn = new TableColumn<>("Action"); // No data type needed

        buttonColumn.setCellFactory(new Callback<TableColumn<Entity, Void>, TableCell<Entity, Void>>() {
            @Override
            public TableCell<Entity, Void> call(final TableColumn<Entity, Void> param) {
                final TableCell<Entity, Void> cell = new TableCell<Entity, Void>() {

                    private final Button btn = new Button("Do Something");

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
        columns.add(buttonColumn);
        entityTableView.getColumns().addAll(columns);

        ObservableList<Entity> entities = FXCollections.observableArrayList(entityList);
        entityTableView.setItems(entities);
    }

    private void addEntity() {
        T entity = EntityCreator.createInstance(entityClass);
        SceneHelper.<AddBaseController>addNode(DisplayBase, Nodes.ADDBASE, new AddBaseController(entity));
    }
}