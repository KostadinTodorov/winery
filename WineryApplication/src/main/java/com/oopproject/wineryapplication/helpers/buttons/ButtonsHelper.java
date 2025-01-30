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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Map;

public class ButtonsHelper {

    public static class ButtonAction {
        private final String label;
        private final EntityProvider entityProvider;

        // Using this interface instantiation it can provide whatever the method usage I'm throwing in it
        public ButtonAction(String label, EntityProvider entityProvider) {
            this.label = label;
            this.entityProvider = entityProvider;
        }

        public ButtonAction(String label) {
            this.label = label;
            this.entityProvider = null;
        }

        public String getLabel() {
            return label;
        }

        public void execute(Scene scene) throws IOException {
            try {
                Entity entity = entityProvider.provide();
                ButtonsHelper.displayBase((AnchorPane) scene.lookup("#placeHolderAnchorPane"), entity);
            } catch (NullPointerException e) {
                LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.ERROR, e.getMessage());
                throw new NullPointerException();
            }
        }

        public void execute(Scene scene, String category) throws IOException {
            if (this.entityProvider == null) {
                ButtonsHelper.displayCategoryButtons((HBox) scene.lookup("#placeHolderHBox"), category);
            }
            else {
                LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.ERROR, "entityProvider had to be null");
                throw new RuntimeException("entityProvider had to be null");
            }
        }
    }

    public static void setButtonsFor(ButtonsMappingRegisters actionBitmap, VBox vboxHolder, Map<Integer, ButtonAction> actionMap) {

        LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.INFO, "Generate category buttons");

        Integer actionInstance = actionBitmap.getButtonsMapping();

        for (Map.Entry<Integer, ButtonAction> entry : actionMap.entrySet()) {
            int bitPosition = entry.getKey();
            ButtonAction action = entry.getValue();

            if ((actionInstance & (1 << bitPosition)) != 0) { // Check if the bit is set
                Button button = new Button(action.getLabel());
                button.setOnAction(e -> {
                    try {
                        action.execute(vboxHolder.getScene(), action.getLabel());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                button.setMaxWidth(Double.MAX_VALUE);
                button.setMaxHeight(Double.MAX_VALUE);
                vboxHolder.getChildren().add(button);
            }
        }
    }


    public static void setButtonsFor(ButtonsMappingRegisters actionBitmap, HBox hboxHolder, Map<Integer, ButtonAction> actionMap) {

        LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.INFO, "Generate entities buttons");

        Integer actionInstance = actionBitmap.getButtonsMapping();

        for (Map.Entry<Integer, ButtonAction> entry : actionMap.entrySet()) {
            int bitPosition = entry.getKey();
            ButtonAction action = entry.getValue();

            if ((actionInstance & (1 << bitPosition)) != 0) { // Check if the bit is set
                Button button = new Button(action.getLabel());
                button.setOnAction(e -> {
                    try {
                        action.execute(hboxHolder.getScene());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                button.setMaxWidth(Double.MAX_VALUE);

                hboxHolder.getChildren().add(button);
            }
        }
        hboxHolder.setAlignment(Pos.CENTER);
    }


    protected static void displayBase(AnchorPane parentHolder, Entity entity) throws IOException {
        LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.INFO, String.format("Opening a display controller for Entity <[ %s ]>", entity.getClass().getSimpleName() ));

        SceneHelper.<DisplayBaseController>addNode(parentHolder, Nodes.DISPLAYBASE, new DisplayBaseController(entity.getClass()));
    }

    protected static void displayCategoryButtons(HBox placeHolderHBox, String category) throws IOException {
        LoggerHelper.logData(ButtonsHelper.class, LoggerLevels.INFO, String.format("Loading buttons for category [ %s ]", category ));

        ButtonsMapHolderForEachEntity entitiesMap = new ButtonsMapHolderForEachEntity();
        placeHolderHBox.getChildren().clear();
        ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.valueOf(category.toUpperCase().replaceAll("\\s", "")), placeHolderHBox, entitiesMap.getActionMap());
    }
}
