package com.oopproject.wineryapplication;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ButtonsHelper {



    public static void setButtonsFor(ButtonsMappingRegisters actionBitmap, VBox vboxHolder) {
        Integer actionInstance = actionBitmap.getButtonsMapping();
        String[] predefinedActions = {"manage workers", "manage wine inventory", "manage orders", "manage machines"};
        Runnable[] predefinedHandlers = {
                () -> {
                    try {
                        displayWorkers( (BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent() );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        displayWineInventory( (BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent() );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        switchToLogin();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        switchToLogin();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        };

        for (int i = 0; i < predefinedActions.length; i++) {
            if ((actionInstance & (1 << i)) != 0) { // Check if the i-th bit is set
                Button button = new Button(predefinedActions[i]);
                int finalI = i;
                button.setOnAction(e -> predefinedHandlers[finalI].run());
                button.setMaxWidth(Double.MAX_VALUE);
                button.setMaxHeight(Double.MAX_VALUE);
                vboxHolder.getChildren().add(button);
            }
        }
    }

    protected static void displayWorkers(BorderPane parentHolder) throws IOException {
        System.out.println("Displaying workers...");
        SceneHelper.addNode((Pane)parentHolder.lookup("#placeHolderAnchorPane"), Nodes.BOTTLEDWINEINVENTORY);
    }

    protected static void displayWineInventory(BorderPane parentHolder) throws IOException {
        System.out.println("Displaying wine...");
        SceneHelper.addNode((Pane)parentHolder.lookup("#placeHolderAnchorPane"), Nodes.BOTTLEDWINEINVENTORY);
    }

    protected static void switchToLogin() throws IOException {
        SceneHelper.switchTo(Scenes.LOG);
    }
}
