package com.oopproject.wineryapplication.helpers.buttons;

import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.controller.DisplayBaseController;
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
                ButtonsHelper.addButtons(ButtonsMappingRegisters.valueOf(getLabel().toUpperCase().replaceAll("\\s", "")), placeHolderHBox, entitiesMap.getActionMap(), true);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (NullPointerException e) {
                throw new NullPointerException();
            }
        }
    }

    // Specific action for loading different notification FXMLs  ---------------------------------------------
    public static class NotificationButtonAction extends ButtonAction {

        public NotificationButtonAction(String label) {
            super(label);
        }

        @Override
        public void execute(Scene scene) throws IOException {
            // TODO - add functionality
        }
    }


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
