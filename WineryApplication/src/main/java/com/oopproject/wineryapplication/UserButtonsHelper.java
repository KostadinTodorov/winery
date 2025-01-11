package com.oopproject.wineryapplication;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class UserButtonsHelper {



    public static void setUserWithBitmap(VBox vboxHolder, UserButtonsMapping actionBitmap) {
        Integer actionInstance = actionBitmap.getUserMapping();
        String[] predefinedActions = {"Action1", "Action2", "Action3", "Action4"};
        Runnable[] predefinedHandlers = {
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
                vboxHolder.getChildren().add(button);
            }
        }
    }

    protected static void switchToLogin() throws IOException {
        SceneHelper.switchTo(Scenes.LOG);
    }
}
