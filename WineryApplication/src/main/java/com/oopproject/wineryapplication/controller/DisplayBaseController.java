package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
//import com.oopproject.wineryapplication.helpers.Nodes;
//import com.oopproject.wineryapplication.helpers.SceneHelper;
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
 * Класът {@code DisplayBaseController} е базов контролер, отговорен за управление на
 * показването, редактирането и изтриването на обекти от определен обектен клас
 * в свързана таблица с визуализирани данни.
 *
 * Класът се използва за следните задачи:
 * <ul>
 * <li>Инициализация на таблицата за показване на данни за обекти от дадения клас.</li>
 * <li>Добавяне на функционалности за изтриване и редактиране на редове от таблицата.</li>
 * <li>Обработка на взаимодействията с бутоните за добавяне, редактиране и изтриване.</li>
 * <li>Създаване на предупреждения за успех и неуспех на извършени действия.</li>
 * </ul>
 *
 * @param <T> Типът на обектите, които се управляват от този контролер. Обектът трябва
 *            да е наследник на {@link Entity}.
 */
public class DisplayBaseController<T extends Entity> {

    /**
     * Класът {@code DeleteBase} предоставя функционалност за изтриване на обект,
     * идентифициран чрез уникалния си идентификатор {@code id}. Класът работи с
     * данни, като използва {@link TemplateDao}.
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
         * Изтрива обект, идентифициран чрез уникалния си идентификатор {@code id}, от базата данни.
         *
         * Тази операция използва {@link TemplateDao}, който предоставя CRUD функционалности за управление на данни
         * в базата. Методът се базира на класа {@code entityClass}, който репрезентира класа на съответния обект.
         *
         * @return {@code true}, ако обектът е успешно изтрит от базата данни;
         *         {@code false}, ако обектът не съществува или операцията е неуспешна.
         * @see TemplateDao#delete(int)
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
     * Инициализира основните компоненти на текущия потребителски интерфейс.
     * Извиква методи за настройка и конфигуриране на бутон и таблица.
     * <p>
     * Този метод се извиква автоматично от JavaFX рамката по време на зареждането на контролера.
     * </p>
     * <ul>
     * <li>{@link #initializeAddButton()} - настройва функционалностите на бутона за добавяне.</li>
     * <li>{@link #initializeTableView()} - настройва изгледа и параметрите на таблицата.</li>
     * </ul>
     */
    @FXML
    public void initialize() {
        initializeAddButton();
        initializeTableView();
    }

    /**
     * Инициализира {@code add} бутона и задава неговото действие при кликване.
     * <p>
     * Логиката за обработка на действието е дефинирана в метода {@link #addEntity()}.
     * Бутонът получава действие чрез {@code setOnAction}, което изпълнява ламбда функция
     * за извикване на метода {@code addEntity}.
     * </p>
     */
    private void initializeAddButton() {
        add.setOnAction(e -> addEntity());
    }

    /**
     * Инициализира изгледа на таблицата и попълва колоните и данните.
     *
     * Методът създава колони, добавя бутони за изтриване и редактиране
     * като отделни колони, добавя тези колони към {@link TableView},
     * а също така попълва таблицата със списък от ентитети.
     *
     * @implNote Методът използва {@link FXCollections#observableArrayList}
     * за работа с ObservableList от ентитети.
     * @see #createColumns()
     * @see #addDeleteButtonColumn(List)
     * @see #addEditButtonColumn(List)
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
     * Създава колекции от {@link TableColumn}, базирани на полетата,
     * дефинирани в {@code entityClass}. Методът отразява всички
     * декларирани полета на класа и за всяко от тях създава екземпляр
     * на {@link TableColumn}, който се добавя към новосъздаден списък.
     * Стойностите се задават с {@link PropertyValueFactory} използвайки
     * името на полетата.
     *
     * @return Списък от {@link TableColumn} елементи ({@link List}),
     * които представляват колони за визуализация на полетата на дадения клас.
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
     * Добавя колона с бутон за изтриване към предоставения списък
     * {@link TableColumn} обекти. Тази колона съдържа бутони, които
     * позволяват на потребителя да изтрие избрания {@link Entity}.
     * Методът създава нова колона и задава нейната {@link TableColumn#setCellFactory(Callback)}
     * чрез {@link #createDeleteButtonCellFactory()}, след което я добавя към предоставения списък.
     *
     * @param columns списък от {@link TableColumn}, към който ще бъде
     *                добавена новата колона за изтриване.
     * @return {@inheritDoc}
     */
    private void addDeleteButtonColumn(List<TableColumn<Entity, ?>> columns) {
        TableColumn<Entity, Void> deleteButtonColumn = new TableColumn<>("Delete");
        deleteButtonColumn.setCellFactory(createDeleteButtonCellFactory());
        columns.add(deleteButtonColumn);
    }

    /**
     * Метод, който създава фабрика за клетки с бутон за изтриване. Това позволява
     * на всяка клетка в съответната колона на таблицата да съдържа бутон за
     * изтриване на елемент от таблицата.
     *
     * Всяка клетка съдържа {@link Button}, който, когато бъде натиснат,
     * извиква действия по изтриване на съответния елемент. Методът включва:
     * <ul>
     *     <li>Потвърждение на изтриването чрез диалог.</li>
     *     <li>Изпълнение на логика за изтриване на елемента чрез метода {@code deleteEntity()}.</li>
     *     <li>Показване на съобщения за успех или грешка.</li>
     *     <li>Премахване на елемента от списъка в {@link TableView}, ако е изтрит успешно.</li>
     * </ul>
     *
     * @return {@inheritDoc} Фабрика от тип {@link Callback}, която генерира клетки
     * с бутон за изтриване за {@link TableColumn} от тип {@code <Entity, Void>}.
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
     * Показва диалог за потвърждение на изтриване.
     *
     * Този метод създава {@link Alert} от тип {@code Alert.AlertType.CONFIRMATION},
     * който моли потребителя да потвърди изтриването на даден елемент. Диалогът
     * съдържа заглавие, текст за заглавна част и съобщение, което предупреждава
     * потребителя, че това действие не може да бъде възстановено.
     *
     * @return {@code true}, ако потребителят потвърди операцията чрез натискане
     * на {@link ButtonType#OK}, иначе {@code false}.
     */
    private boolean showDeleteConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("Confirm deletion");
        alert.setContentText("This cannot be reverted!");
        return alert.showAndWait().filter(result -> result == ButtonType.OK).isPresent();
    }

    /**
     * Изтрива съществуващ {@link Entity} от системата.
     *
     * @param entity обекта от тип {@link Entity}, който трябва да бъде изтрит.
     *               Не може да бъде {@code null}.
     * @return {@code true}, ако обектът е изтрит успешно; {@code false}, ако изтриването е неуспешно.
     */
    private boolean deleteEntity(Entity entity) {
        return new DeleteBase(entity).delete();
    }

    /**
     * Показва информационен прозорец (alert), който уведомява потребителя, че
     * избраното от него съдържание е успешно изтрито.
     * <p>
     * Използва {@code showAlert} метод с тип {@link Alert.AlertType#INFORMATION},
     * за да създаде и покаже съобщението.
     *
     * @see Alert
     * @see #showAlert(Alert.AlertType, String, String)
     */
    private void showDeleteSuccessAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Item deleted successfully!", "You have successfully deleted this item!");
    }

    /**
     * Показва информационен предупредителен прозорец при неуспешно изтриване на елемент.
     * Методът се използва за уведомяване на потребителя, че даден елемент не може да бъде изтрит,
     * защото е рефериран в други таблици.
     *
     * Използва {@link #showAlert(Alert.AlertType, String, String)} за създаване и показване на прозореца за предупреждение.
     */
    private void showDeleteFailureAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Item wasn't deleted successfully!", "Items cannot be deleted if they are referenced in other tables.");
    }

    /**
     * Показва предупредителен или информационен диалогов прозорец {@link Alert}.
     *
     * @param type типът на съобщението, което ще се покаже, използвайки {@link Alert.AlertType}.
     *             Може да бъде стойности като {@code Alert.AlertType.INFORMATION},
     *             {@code Alert.AlertType.WARNING}, {@code Alert.AlertType.ERROR} и т.н.
     * @param title заглавие на прозореца, което ще се покаже в горната част на диалоговия прозорец.
     * @param content текстово съдържание на диалоговия прозорец, което предоставя допълнителна информация за потребителя.
     * @return {@inheritDoc}
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    /**
     * Добавя нова колона с бутон за редактиране към списъка с колони в таблицата.
     * Колоната съдържа бутон "Edit", който може да бъде настроен с подходяща
     * функционалност чрез {@link #createEditButtonCellFactory()}.
     *
     * @param columns списък от {@code TableColumn<Entity, ?>}, към който ще се добави
     * новата колона с бутон за редактиране
     * (например {@code List<TableColumn<Entity, ?>>}).
     */
    private void addEditButtonColumn(List<TableColumn<Entity, ?>> columns) {
        TableColumn<Entity, Void> editButtonColumn = new TableColumn<>("Edit");
        editButtonColumn.setCellFactory(createEditButtonCellFactory());
        columns.add(editButtonColumn);
    }

    /**
     * Създава фабрика за клетки, която добавя бутон "Edit" в дадена колона
     * на таблица {@link TableView}. Бутонът позволява редактирането на
     * съответния елемент от таблицата.
     *
     * Всяка клетка, съдържаща бутона, извлича свързания обект
     * {@link Entity} от таблицата и извиква метода {@code editEntity(Entity)}
     * за редакция на този обект.
     *
     * @return {@link Callback}, който връща {@link TableCell} със
     * бутон "Edit" за дадена колона {@link TableColumn}.
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
     * Методът {@code addEntity} добавя нов екземпляр на ентитет, използвайки
     * предоставения клас {@code entityClass}, чрез създаване на негов
     * инстанция и предаването й на контролера {@link AddBaseController}.
     * <p>
     * Методът се грижи за създаването и управлението на нов ентитет в рамките
     * на сцената, като използва {@link SceneHelper#addNode}.
     * </p>
     *
     * @throws RuntimeException ако възникне грешка при създаването на
     *                          инстанция на ентитета, включително
     *                          недостъпен конструктор или рефлекционна грешка.
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
     * Редактира съществуващ {@link Entity} като създава нов контролер и го добавя към сцена.
     * Използва {@link SceneHelper} за зареждане на възел {@code Nodes.EDITBASE}.
     *
     * @param entity Обект от тип {@link Entity}, който ще бъде редактиран.
     * @return {@inheritDoc}
     */
    private void editEntity(Entity entity) {
        SceneHelper.<EditBaseController>addNode(DisplayBase, Nodes.EDITBASE, new EditBaseController(entity));
    }
}