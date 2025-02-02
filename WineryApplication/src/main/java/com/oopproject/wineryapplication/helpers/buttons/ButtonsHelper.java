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
 * The ButtonsHelper class provides functionality and actions related to button creation and execution
 * within a JavaFX UI context. It defines and manages various types of button actions, each implementing
 * specific behavior to interact with different aspects of the application.
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
     * Represents a specific button action for creating and handling entities in the application.
     * This class extends the {@link ButtonAction} to provide functionality specialized for
     * interacting with and managing {@link Entity} objects through an {@link EntityProvider}.
     *
     * Upon execution, this action creates an entity using the provided {@link EntityProvider},
     * logs the relevant information, and initializes a display controller for the created entity.
     *
     * Any failure in obtaining or displaying the entity is logged, and a {@link NullPointerException}
     * may be thrown if a null value is encountered during the operation.
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
     * Represents a specific type of button action that generates category-related buttons within a
     * user interface and configures them based on a predefined mapping.
     *
     * This class extends the {@code ButtonAction} class and is intended to handle actions
     * for categories by dynamically creating buttons associated with entities and configuring their behavior.
     * The category is determined by the label provided during the instantiation of the class.
     *
     * The {@code execute} method is overridden to implement the functionality required to
     * generate buttons for entities associated with the selected category. The implementation
     * clears existing buttons in the UI placeholder (`HBox`) and populates it with new buttons
     * based on a mapping registry (`ButtonsMappingRegisters`) aligned to the category.
     *
     * Logging is utilized during execution to trace the creation of buttons and their association
     * with the given category label. Exceptions are handled for situations such as invalid category
     * names or null references.
     *
     * Note: This class provides functionality specific to the application's entity-related
     * button configuration mechanism. It works in conjunction with other components in the
     * system, such as `ButtonsMapHolderForEachEntity`, `LoggerHelper`, and category-specific
     * button mappings.
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
     * Represents a specific action for loading different notification FXML files.
     * This class extends the abstract {@code ButtonAction} class to provide a concrete implementation for handling button
     * operations related to notifications. It maps the button's label to the corresponding enum value, loads the associated
     * FXML, and initializes it with a specified controller.
     *
     * Responsibilities:
     * - Override the {@code execute} method to define the specific behavior for interacting with the scene when the button is clicked.
     * - Map button labels to their respective notification FXMLs using {@code ButtonsNotificationsRegisters}.
     * - Log operations and handle any exceptions that may arise during execution.
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
     * Adds buttons to a container based on the provided action bitmap and action map.
     * This method dynamically generates buttons for a given {@link Pane}, associates
     * them with actions, and organizes them either in a {@link VBox} or an {@link HBox},
     * depending on the provided container type.
     *
     * The method checks which actions, represented by bits in the {@code actionBitmap},
     * should have corresponding buttons created. For each bit set in the bitmap,
     * the corresponding {@link ButtonAction} is mapped to a button, which is then added
     * to the container.
     *
     * @param <T>       the type of container, extending {@link Pane}, to which the buttons
     *                  will be added. Can be a {@link VBox} or {@link HBox}.
     * @param actionBitmap the {@link ButtonsMappingRegisters} instance representing the bitmap
     *                  of actions. Each bit in the bitmap corresponds to whether an action
     *                  is enabled or not.
     * @param holder    the container, of type {@link Pane}, to which the generated buttons will
     *                  be added. Supported types are {@link VBox} and {@link HBox}.
     * @param actionMap a map associating integer bit positions with {@link ButtonAction} instances.
     *                  Each entry maps a bit position to the action associated with it.
     * @param forVBox   a flag indicating whether the buttons should be added to a {@link VBox}.
     *                  If {@code true}, the method assumes {@code holder} is a {@link VBox};
     *                  otherwise, it assumes {@code holder} is an {@link HBox}.
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
