package com.oopproject.wineryapplication.access.entities.mappers;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Представлява конкретна имплементация на интерфейса {@link EntityFieldMapper}.
 * Този клас предоставя функционалност за генериране на карта, която свързва полетата на специфичен
 * клас {@link Entity} със съответните JavaFX UI възли.
 *
 * Този клас се използва за динамично съпоставяне на полета на entity-то към подходящи UI компоненти,
 * позволявайки автоматично генериране на потребителски интерфейси за управление на entity-та.
 */
public class EntityTypeNodeMapper implements EntityFieldMapper{
    private final Class<? extends Entity> entityClass;

    /**
     * Конструктор за класа EntityTypeNodeMapper.
     * Създава нов инстанция, която съпоставя полета на указания {@code entityClass}
     * към съответните JavaFX UI възли.
     *
     * @param entityClass типът на класа на {@code Entity}, който ще се използва за съпоставяне поле-към-възел.
     */
    public EntityTypeNodeMapper(Class<? extends Entity> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Генерира и връща съпоставяне между декларираните полета на класа entity
     * и техните съответни JavaFX UI възли. Методът динамично присвоява подходящи
     * UI компоненти въз основа на типа на всяко поле.
     *
     * Полетата от тип {@link Entity} са свързани с {@link ComboBox}, попълнен с
     * всички инстанции на типа entity. Булевите полета са съпоставени с {@link CheckBox},
     * полетата от тип {@link LocalDate} получават {@link DatePicker}, а другите типове полета са
     * свързани с {@link TextField} с подкана, показваща типа на полето.
     *
     * @return карта, където ключовете са обекти {@link Field}, представляващи полетата на
     *         класа entity, а стойностите са съответни обекти {@link Node} за UI.
     */
    public Map<Field, Node> getFieldNodeMap() {
        Map<Field, Node> fieldNodeMap = new HashMap<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            if (Entity.class.isAssignableFrom(fieldType)) {
                Class<Entity> entityField = (Class<Entity>) fieldType;
                ComboBox<Entity> entityComboBox = new ComboBox<>();
                List<Entity> entities = new TemplateDao<Entity>(entityField).getAll();
                ObservableList<Entity> observableEntities = FXCollections.observableArrayList(entities);
                entityComboBox.setItems(observableEntities);
                fieldNodeMap.put(field, entityComboBox);
            } else if (fieldType.equals(Boolean.class)) {
                CheckBox checkBox = new CheckBox();
                checkBox.setId(fieldName);
                fieldNodeMap.put(field, checkBox);
            } else if (fieldType.equals(LocalDate.class)) {
                DatePicker datePicker = new DatePicker();
                datePicker.setId(fieldName);
                fieldNodeMap.put(field, datePicker);
            } else {
                TextField textField = new TextField();
                textField.setPromptText(fieldType.getTypeName());
                textField.setId(fieldName);
                fieldNodeMap.put(field, textField);
            }
        }
        return fieldNodeMap;
    }

    /**
     * Извлича типа на класа на entity-то, свързано с този mapper.
     *
     * Този метод предоставя достъп до класа entity, който се използва за създаване
     * на съпоставяния поле-към-възел или други операции, изискващи познаване на
     * типа на entity-то. Върнатият тип на класа се определя по време на
     * инстанцирането на съдържащия клас.
     *
     * @return типът на класа на свързаното entity, разширяващ {@code Entity}.
     */
    protected Class<? extends Entity> getEntityClass() {
        return entityClass;
    }
}