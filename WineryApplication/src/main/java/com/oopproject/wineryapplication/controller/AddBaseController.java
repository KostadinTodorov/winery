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
 * Класът {@code AddBaseController} предоставя логиката за динамично генериране на графичен интерфейс за
 * създаване и редактиране на обекти от тип {@link Entity}.
 *
 * Този клас работи с JavaFX елементи и се грижи за правилното свързване между полетата на обекта
 * {@link Entity} и съответстващите им UI компоненти. Основните му функционалности включват:
 * - Генериране на графичен потребителски интерфейс на база на полетата на обекта {@link Entity}.
 * - Обработка и запазване на данните, въведени от потребителя, във външна база данни.
 * - Предоставяне на обратна връзка на потребителя относно успешно или неуспешно съхранение.
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
     * Инициализира потребителския интерфейс на контролера, генерира динамични възли за полетата
     * на entity-то и добавя "Save" бутон с функционалност за записване.
     *
     * Методът изпълнява следните стъпки:
     * - Създава нов бутон "Save" и свързва неговото действие с метода {@link #saveButton()}.
     * - Добавя бутона към контейнера {@code entityProps}.
     * - Извиква {@code AddBase#toFieldNodesMap(EntityFieldMapper)}, за да създаде карта от полета към съответните UI възли.
     * - Подготвя и генерира визуални компоненти чрез {@link #generateNodes(Map)} за всяко поле.
     *
     * @see #saveButton()
     * @see #generateNodes(Map)
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
     * Генерира визуални компоненти (възли) за дадените полета в мапа {@code fieldNodeMap}
     * и ги добавя към потребителския интерфейс.
     *
     * Методът изпълнява следните действия:
     * - Проверява типа и името на всяко поле от мапа.
     * - Ако типът на полето не е {@code Set.class} и името му не е "id", създава етикет {@link Label}
     *   за името на полето и го добавя заедно със съответния възел {@link Node} към контейнера {@code entityProps}.
     *
     * @param fieldNodeMap карта, която съдържа като ключове обекти от тип {@code Field} и като стойности –
     *                     съответните UI компоненти (възли) {@link Node}, които трябва да бъдат визуализирани.
     *
     * @see Field#getType()
     * @see Field#getName()
     * @see Label
     * @see Node
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
     * Бутона за записване обработва логиката за валидиране и запис на данни в базата.
     *
     * Този метод извършва следните стъпки:
     * - Проверява дали данните са правилно попълнени чрез извикване на метода {@link #setEntity()}.
     * - Ако валидацията е успешна, се опитва да запише данните в базата чрез {@link #saveEntity()}.
     * - Ако записът е успешен, извежда информационно съобщение за успех и изчиства UI възлите чрез {@link #clearNodes()}.
     * - Ако записът е неуспешен, извежда предупреждение, че е възникнала грешка.
     * - Ако валидацията не е успешна, извежда уведомление за неправилно попълнени полета.
     *
     * Методът използва клас {@link Alert} за извеждане на визуални съобщения при всяко действие.
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
     * Настройва и инициализира текущата инстанция на {@link Entity} чрез фабрика за създаване
     * на обекти {@link EntityFactory}. Методът се опитва да създаде нова инстанция
     * и да я зададе като стойност на полето {@code entity}.
     *
     * Извикването на фабриката {@link GenericEntityFactory} може да доведе до възникване
     * на изключение, в случай на проблем при създаването на ентитета. Връщаната стойност
     * индикира успеха или провала на операцията.
     *
     * @return {@code true}, ако създаването и задаването на ентитета е успешно;
     *         {@code false}, ако възникне изключение.
     *
     * @see EntityFactory#createEntity()
     * @see GenericEntityFactory
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
