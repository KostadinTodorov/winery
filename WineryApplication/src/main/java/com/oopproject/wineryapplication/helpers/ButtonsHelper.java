package com.oopproject.wineryapplication.helpers;

import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.controller.DisplayBaseController;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Map;

public class ButtonsHelper {

    public static class ButtonAction {
        private final String label;
        private final EntityProvider entityProvider;

        public ButtonAction(String label, EntityProvider entityProvider) {
            this.label = label;
            this.entityProvider = entityProvider;
        }

        public String getLabel() {
            return label;
        }

        public void execute(BorderPane parentHolder) throws IOException {
            Entity entity = entityProvider.provide();
            ButtonsHelper.displayEntity((AnchorPane) parentHolder.lookup("#placeHolderAnchorPane"), entity);
        }
    }

    public static void setButtonsFor(ButtonsMappingRegisters actionBitmap, VBox vboxHolder, Map<Integer, ButtonAction> actionMap) {
        Integer actionInstance = actionBitmap.getButtonsMapping();

        for (Map.Entry<Integer, ButtonAction> entry : actionMap.entrySet()) {
            int bitPosition = entry.getKey();
            ButtonAction action = entry.getValue();

            if ((actionInstance & (1 << bitPosition)) != 0) { // Check if the bit is set
                Button button = new Button(action.getLabel());
                button.setOnAction(e -> {
                    try {
                        action.execute((BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent());
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

    protected static void displayEntity(AnchorPane parentHolder, Entity entity) throws IOException {
        System.out.println("Displaying Entity = ");
        SceneHelper.<DisplayBaseController>addNode(parentHolder,Nodes.DISPLAYBASE, new DisplayBaseController(entity.getClass()));
    }
}
