package com.oopproject.wineryapplication.access.entities.creator;

import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.access.entities.mappers.EntityFieldMapper;
import com.oopproject.wineryapplication.access.entities.mappers.EntityTypeNodeMapper;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * GenericEntityFactory е конкретна имплементация на интерфейса EntityFactory,
 * отговаряща за създаването и инициализирането на инстанции на класа {@link Entity} в javafx среда.
 * Използва съпоставяне между полетата на entity-то и UI компонентите, представени от {@link Node},
 * за да попълни полетата на entity-то със съответните стойности от различните имплементации на {@code class Node}.
 */
public class GenericEntityFactory <T extends Entity> implements EntityFactory{
    private final T entity;
    private Map<Field, Node> fieldNodeMap;


    /**
     * Конструира нов инстанция на GenericEntityFactory.
     *
     * @param entity Базовият обект entity, използван като "generic" прототип за създаване на нови entity-та, се третира като празен.
     * @param fieldNodeMap Карта, свързваща полетата на entity-то със съответните им графични node представяния. Очаква се картата да съвпада
     */
    public GenericEntityFactory(T entity, Map<Field, Node> fieldNodeMap) {
        this.entity = entity;
        this.fieldNodeMap = fieldNodeMap;
    }

    @Override
    public Entity createEntity() {
        return createEntity(entity, fieldNodeMap);
    }

    /**
     * Създава и инициализира обект {@link Entity}, като съпоставя полетата му със съответните GUI възли.
     * Този метод итерира през полетата на даденото entity, съпоставя всяко поле въз основа на неговия тип
     * към предоставеното съпоставяне поле-възел и задава съответните стойности на полетата на entity-то.
     *
     * @param entity Базовият обект entity, който трябва да бъде инициализиран. Полетата му ще бъдат попълнени въз основа на предоставеното съпоставяне.
     * @param fieldNodeMap Карта, свързваща полетата на entity-то със съответните им GUI node представяния.
     *                     Ключовете са полетата на entity-то, а стойностите са GUI възли като TextField, ComboBox и т.н.
     * @return Актуализираният обект {@link Entity} с попълнени полета въз основа на даденото съпоставяне поле-възел.
     */
    private Entity createEntity(T entity, Map<Field, Node> fieldNodeMap) {
        Set<Field> entityFields = entity.toFieldNodesMap(new EntityTypeNodeMapper(entity.getClass())).keySet();

        for (Field field : entityFields) {
            field.setAccessible(true);

            Node node = fieldNodeMap.get(field);

            Class<?> fieldType = field.getType();
            try {
                if (Entity.class.isAssignableFrom(fieldType)) {
                    ComboBox<Entity> entityComboBox = (ComboBox<Entity>) node;
                    if (entityComboBox.getValue() != null) {
                        field.set(entity, entityComboBox.getValue());
                    }
                } else {
                    setFieldValue(field, entity, node, fieldType);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    /**
     * Задава стойността на указано поле в обект entity въз основа на стойността, взета
     * от съответния GUI възел, като например TextField, CheckBox или DatePicker.
     * Присвояването на стойността зависи от типа на полето.
     *
     * @param field Полето на обекта entity, чиято стойност трябва да бъде зададена.
     * @param entity Обектът entity, към който принадлежи полето.
     * @param node GUI възелът, съответстващ на полето, използван за извличане на входната стойност.
     * @param fieldType Типът данни на полето, за да се осигури подходяща обработка и преобразуване на типа.
     * @throws IllegalAccessException Ако полето не може да бъде достъпено или модифицирано.
     * @throws IllegalArgumentException Ако типът на полето не се поддържа или възелът е невалиден.
     */
    private void setFieldValue(Field field, Entity entity, Node node, Class<?> fieldType) throws IllegalAccessException {
        switch (node) {
            case TextField textField -> {
                String value = textField.getText();
                if (value != null && !value.isEmpty()) {
                    if (fieldType == String.class) {
                        field.set(entity, value);
                    } else if (fieldType == Short.class || fieldType == short.class) {
                        field.set(entity, Short.parseShort(value));
                    } else if (fieldType == Integer.class || fieldType == int.class) {
                        field.set(entity, Integer.parseInt(value));
                    } else if (fieldType == Long.class || fieldType == long.class) {
                        field.set(entity, Long.parseLong(value));
                    } else if (fieldType == Float.class || fieldType == float.class) {
                        field.set(entity, Float.parseFloat(value));
                    } else if (fieldType == Double.class || fieldType == double.class) {
                        field.set(entity, Double.parseDouble(value));
                    } else {
                        System.err.println("Unsupported field type: " + fieldType.getName());
                    }
                }
            }
            case CheckBox checkBox when (fieldType == Boolean.class || fieldType == boolean.class) ->
                    field.set(entity, checkBox.isSelected());
            case DatePicker datePicker when fieldType == java.time.LocalDate.class ->{
                if (datePicker.getValue() != null) {
                    field.set(entity, datePicker.getValue());
                }
            }
            case null, default -> throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
        }
    }


}