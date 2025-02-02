package com.oopproject.wineryapplication.helpers.buttons;

import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.controller.DisplayBaseController;
import com.oopproject.wineryapplication.controller.OperationsController;
import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import com.oopproject.wineryapplication.helpers.scenes.Nodes;
import com.oopproject.wineryapplication.helpers.scenes.SceneHelper;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Map;

/**
 * Класът {@code ButtonsHelper} предоставя помощни средства за генериране и работа с различни действия
 * и бутони в потребителския интерфейс. Основната му функционалност е свързана със създаването на
 * бутони, асоциирани с конкретни действия, и добавянето им към контейнери като {@link VBox} или {@link HBox},
 * както и дефинирането на специфични действия за работа с ентитети, категории и операции.
 *
 * Този клас съдържа вътрешни класове, които разширяват {@code ButtonAction} за реализиране на конкретни
 * функционалности, както и методи за динамично създаване и настройване на бутони.
 */
public class ButtonsHelper {

    public abstract static class ButtonAction {
        private final String label;

        public ButtonAction(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public abstract void execute(Scene scene) throws IOException;
    }

    /**
     * Класът {@code EntityButtonAction} представлява конкретен {@link ButtonAction}, който е предназначен
     * за създаване и обработване на бутон за взаимодействие със специфичен {@link Entity}.
     * Той използва {@link EntityProvider}, за да осигури екземпляри на {@link Entity}, когато е необходимо.
     *
     * {@code EntityButtonAction} изпълнява действията, свързани със създаването на елемент,
     * извеждането на лог и визуализация на съответния контролер чрез добавяне на възел в потребителския интерфейс.
     */
    // Specific action for crating entities buttons --------------------------------------------------------
    public static class EntityButtonAction extends ButtonAction {
        private final EntityProvider entityProvider;

        public EntityButtonAction(String label, EntityProvider entityProvider) {
            super(label);
            this.entityProvider = entityProvider;
        }

        @Override
        public void execute(Scene scene) throws IOException {
            try {
                Entity entity = entityProvider.provide();
                LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.INFO, String.format("Opening a display controller for Entity <[ %s ]>", entity.getClass().getSimpleName() ));
                SceneHelper.<DisplayBaseController>addNode((AnchorPane) scene.lookup("#placeHolderAnchorPane"), Nodes.DISPLAYBASE, new DisplayBaseController(entity.getClass()));
            } catch (NullPointerException e) {
                LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.ERROR, e.getMessage());
                throw new NullPointerException();
            }
        }
    }

    /**
     * Специфично действие за създаване на бутони за категории.
     * Класът {@code CategoryButtonAction} наследява {@link ButtonAction} и е предназначен за
     * управление на действия, свързани с бутони за категории. При изпълнение на метода
     * {@link #execute(Scene)}, той динамично зарежда и добавя съответните бутони в {@link HBox},
     * базирайки се на зададената категория.
     *
     * Конструкторът приема етикет за съответната категория, който служи за идентификация
     * и управление на действието.
     */
    // Specific action for creating category buttons ---------------------------------------------------------
    public static class CategoryButtonAction extends ButtonAction {

        public CategoryButtonAction(String label) {
            super(label);
        }

        @Override
        public void execute(Scene scene) throws IOException {
            try {
                ButtonsMapHolderForEachEntity entitiesMap = new ButtonsMapHolderForEachEntity();
                HBox placeHolderHBox = (HBox) scene.lookup("#placeHolderHBox");

                LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.INFO, String.format("Loading entities buttons for category [ %s ]", getLabel() ));
                placeHolderHBox.getChildren().clear();
                // Gets the button name for example (Manage orders) and aligns it with the name of the bitmap enum register
                ButtonsHelper.addButtons(ButtonsMappingRegisters.valueOf(getLabel().toUpperCase().replaceAll("\\s", "")), placeHolderHBox, entitiesMap.getActionMap(), true);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (NullPointerException e) {
                throw new NullPointerException();
            }
        }
    }

    /**
     * {@code OperationButtonAction} е подклас на {@link ButtonAction}, създаден за изпълнение
     * на специфични действия, свързани с отварянето на известия, използвайки FXML файлове.
     * Той предоставя фунционалност за зареждане и управление на известия чрез поп-ъп прозорци.
     */
    // Specific action for loading different notification FXMLs  ---------------------------------------------
    public static class OperationButtonAction extends ButtonAction {

        public OperationButtonAction(String label) {
            super(label);
        }

        @Override
        public void execute(Scene scene) throws IOException {
            try {
                LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.INFO, String.format("Opening a Notifications Controller for <[ %s ]>", getLabel() ));
                // Gets the button name for example (Orders) and aligns it with the name of the enum, that will refer to the FXML
                SceneHelper.<OperationsController>switchToPopUp(ButtonsNotificationsRegisters.valueOf(getLabel().toUpperCase().replaceAll("\\s", "")), getLabel(), new OperationsController(getLabel()));
            } catch (NullPointerException e) {
                LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.ERROR, e.getMessage());
                throw new NullPointerException();
            }
        }
    }


    /**
     * Добавя бутони към зададен потребителски интерфейс контейнер (тип {@link Pane}), базирайки се
     * на зададената карта за действия на бутоните и активния битов флаг в {@link ButtonsMappingRegisters}.
     * Ако параметърът {@code forVBox} е истинен и контейнерът е от тип {@link VBox}, бутоните ще бъдат добавени
     * към {@link VBox}. В противен случай, ако контейнерът е от тип {@link HBox}, бутоните ще бъдат добавени към него.
     *
     * Бутоните се настройват така, че когато бъдат натиснати, изпълняват действие чрез метода
     * {@link ButtonAction#execute(Scene)}. Ако възникне {@link IOException} по време на изпълнението
     * на задача, тя се прехвърля като {@link RuntimeException}.
     *
     * @param <T>        Типът на контейнера, в който бутоните ще бъдат добавени. Трябва да наследява {@link Pane}.
     * @param actionBitmap Битовата карта за активиране на бутоните. Определя кои бутони да бъдат създадени
     *                     въз основа на активни битове.
     * @param holder      Контейнер (напр. {@link VBox} или {@link HBox}), в който бутоните ще бъдат добавени.
     *                    Типът трябва да бъде {@link Pane} или наследник на него.
     * @param actionMap   Карта, която свързва индекси на бутоните (цели числа) с техните свързани действия
     *                    чрез обекти от тип {@link ButtonAction}.
     * @param forVBox     Флаг, указващ дали контейнерът е от тип {@link VBox}. Ако е истинен, бутоните ще бъдат
     *                    добавени към {@link VBox}. Ако е {@code false}, се очаква контейнерът да е {@link HBox}.
     *
     * @return Няма стойност за връщане.
     */
    public static <T extends Pane> void addButtons(ButtonsMappingRegisters actionBitmap, T holder, Map<Integer, ButtonAction> actionMap, boolean forVBox) {
        Integer actionInstance = actionBitmap.getButtonsMapping();

        for (Map.Entry<Integer, ButtonAction> entry : actionMap.entrySet()) {
            int bitPosition = entry.getKey();
            ButtonAction action = entry.getValue();

            if ((actionInstance & (1 << bitPosition)) != 0) {
                Button button = new Button(action.getLabel());
                button.setOnAction(e -> {
                    try {
                        action.execute(holder.getScene());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                button.setMaxWidth(Double.MAX_VALUE);

                if (forVBox && holder instanceof VBox vbox) {
                    vbox.getChildren().add(button);
                } else if (holder instanceof HBox hbox) {
                    hbox.getChildren().add(button);
                }
            }
        }
        if (holder instanceof HBox hbox) {
            hbox.setAlignment(Pos.CENTER);
        }
    }
}
