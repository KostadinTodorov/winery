package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.creator.EntityFactory;
import com.oopproject.wineryapplication.access.entities.creator.GenericEntityFactory;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.access.entities.mappers.EntityFieldMapper;
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
 * Класът {@code EditBaseController} служи като контролер за редактиране на обекти от тип {@link Entity}
 * чрез JavaFX интерфейси. Той управлява потребителския интерфейс и позволява динамично генериране на елементи
 * в зависимост от структурата на обекта.
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
     * Конструкторът на {@code EditBaseController}, който инициализира нов обект на базата на
     * предоставеното entity. Уверява се, че entity-то има валидно ID, което е необходимо
     * за управлението на неговите данни.
     *
     * @param entity обект {@link Entity}, който се използва за инициализацията на контролера.
     *               Този обект не трябва да бъде {@code null} и трябва да има валидно ID.
     *               Ако {@code entity.getId()} върне {@code null}, се хвърля
     *               {@link IllegalArgumentException}.
     * @throws IllegalArgumentException когато ID на предоставения {@code entity} е {@code null}.
     *                                   Това гарантира, че няма да има опити за управление на
     *                                   entity без валиден идентификатор.
     * @return {@inheritDoc}
     */
    public EditBaseController(Entity entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.entity = entity;
    }

    /**
     * Инициализира потребителския интерфейс за контролера, като извършва следните действия:
     *
     * 1. Създава бутон за запис {@code saveButton} със съответен текст "Save".
     *    Добавя обработчик на събития за този бутон, който извиква метода {@link #saveButton()}
     *    при натискане.
     *
     * 2. Добавя създадения бутон като child елемент в потребителския интерфейс на контейнера
     *    {@code entityProps}.
     *
     * 3. Инициализира {@code fieldNodeMap}, като преобразува полетата на обекта {@code entity}
     *    в карта от полета към UI възли (nodes), използвайки метода {@link Entity#toFieldNodesMap(EntityFieldMapper)}.
     *    Това съпоставяне позволява генерирането на съответните UI възли.
     *
     * 4. Генерира UI възли въз основа на картата {@code fieldNodeMap} чрез извикване на
     *    метода {@link #generateNodes(Map)}. Тези възли се използват за свързването на
     *    полетата на entity-то със съответните визуални елементи.
     *
     * Този метод подготвя интерфейса за взаимодействие с потребителя и връзката му с данните
     * на entity-то.
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
     * Генерира потребителски интерфейс компоненти (възли) за полетата от предоставената карта ({@link Map}),
     * свързваща обекти от тип {@link Field} с техните съответни {@link Node}.
     *
     * Методът преминава през всички записи в предоставената карта ({@code fieldNodeMap}) и за всяко поле:
     * <ul>
     *   <li>Проверява дали типът на полето не е {@link Set}, както и дали името на полето не е {@code "id"}.
     *       Ако някое от тези условия е изпълнено, полето бива пропуснато.</li>
     *   <li>За всички други случаи, създава {@link Label} с името на полето и добавя
     *       този етикет заедно със съответния {@link Node} в потребителския интерфейс, съдържащ се в
     *       {@code entityProps}.</li>
     * </ul>
     *
     * @param fieldNodeMap карта, съдържаща асоциация между
     *                     полетата от тип {@link Field} и тяхната визуализация като {@link Node}.
     *                     Тази карта се използва за създаване на съответстващите елементи в потребителския интерфейс.
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
     * Обработва натискането на бутона за запис, като проверява, инициализира и съхранява
     * предоставеното entity в базата данни.
     *
     * Методът изпълнява следните стъпки:
     * <ul>
     *   <li>Извиква {@link #setEntity()}, за да се увери, че данни от полетата са валидирани
     *       и entity-то е правилно инициализирано.</li>
     *   <li>Ако entity-то е успешно инициализирано, се извиква {@code saveEntity},
     *       за да се опита да запише информацията в базата данни.</li>
     *   <li>На успешен запис: показва {@link Alert} с тип {@link Alert.AlertType#INFORMATION},
     *       указващ, че данните са успешно съхранени, и извиква {@link #clearNodes()}
     *       за почистване на съдържанието на полетата.</li>
     *   <li>Ако възникне грешка при запис: показва {@link Alert}, уведомяващ потребителя за
     *       неуспешна операция.</li>
     *   <li>Ако инициализацията на entity с {@code setEntity} е неуспешна, методът показва
     *       {@link Alert} за неправилно попълнени полета, напомняйки потребителя да
     *       коригира грешките.</li>
     * </ul>
     *
     * Използва {@link #clearNodes()} за почистване на полетата на формуляра след успешен запис.
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
     * Настройва и създава нова инстанция на {@link Entity} чрез фабриката {@link GenericEntityFactory}.
     * Този метод използва {@code entity} и {@code fieldNodeMap} за инициализиране и
     * създаване на обект от типа {@code Entity}.
     *
     * @return {@code true}, ако създаването на обекта е успешно;
     *         {@code false}, ако възникне изключение по време на процеса на създаване.
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
     * Запазва дадената единица в базата данни чрез метода {@code update} на {@link TemplateDao}.
     * Този метод използва {@link Entity#getDao()} за получаване на DAO обекта, свързан с типа на единицата.
     * Ако възникне изключение по време на запазването, методът ще върне {@code false}.
     *
     * @return {@code true}, ако единицата е успешно обновена в базата данни,
     * или {@code false}, ако е възникнала грешка.
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
     * Проверява дали даден клас {@code clazz} имплементира даден интерфейс {@code interfaceClass}.
     * Това включва както директната имплементация на интерфейса, така и имплементациите
     * в наследени класове.
     *
     * @param clazz Класът, който се проверява за имплементация на интерфейса.
     *              Може да бъде всеки обект от тип {@link Class}.
     * @param interfaceClass Интерфейсът, за който се проверява дали е имплементиран.
     *                       Трябва да бъде обект от тип {@link Class}, който представлява интерфейс.
     * @return {@code true} ако класът {@code clazz} или някой от неговите наследени класове
     *         имплементира интерфейса {@code interfaceClass}; {@code false} в противен случай.
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
     * Изчиства съдържанието на всички {@code Node} обекти, съхранени във {@code fieldNodeMap}.
     * Методът преминава през всички стойности в {@code fieldNodeMap} и ги нулира в зависимост
     * от техния специфичен тип:
     * <ul>
     *     <li>За {@link TextField} съдържанието ще се нулира чрез {@link TextField#setText(String)} като
     *     празен низ.</li>
     *     <li>За {@link ComboBox} селекцията ще бъде премахната чрез {@link ComboBox#getSelectionModel()}
     *     и метода {@link SelectionModel#clearSelection()}.</li>
     *     <li>За {@link DatePicker} стойността ще бъде нулирана чрез {@link DatePicker#setValue(java.time.LocalDate)}
     *     като {@code null}.</li>
     *     <li>За {@link CheckBox} селекцията ще бъде изчистена чрез {@link CheckBox#setSelected(boolean)}
     *     чрез задаване на {@code false}.</li>
     * </ul>
     * Всички други типове {@code Node} обекти няма да бъдат променени.
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
