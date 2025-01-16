package com.oopproject.wineryapplication.helpers;

import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.entities.Harvest;
import com.oopproject.wineryapplication.access.entities.WineType;
import com.oopproject.wineryapplication.controller.AddBaseController;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ButtonsHelper {



    public static void setButtonsFor(ButtonsMappingRegisters actionBitmap, VBox vboxHolder) {
        Integer actionInstance = actionBitmap.getButtonsMapping();
        String[] predefinedActions = {"manage workers", "manage wine", "manage harvest", "manage orders"};
        Runnable[] predefinedHandlers = {
                () -> {
                    try {
                        Workers( (BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent() );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        Wine( (BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent() );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        Hrvst( (BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent() );
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

    protected static void Workers(BorderPane parentHolder) throws IOException {
        System.out.println("Displaying workers...");
        SceneHelper.<AddBaseController>addNode((Pane)parentHolder.lookup("#placeHolderAnchorPane"),Nodes.ADDBASE, new AddBaseController(new Employee()));
    }

    protected static void Wine(BorderPane parentHolder) throws IOException {
        System.out.println("Displaying wine...");
        SceneHelper.<AddBaseController>addNode((Pane)parentHolder.lookup("#placeHolderAnchorPane"),Nodes.ADDBASE, new AddBaseController(new WineType()));
    }

    protected static void Hrvst(BorderPane parentHolder) throws IOException {
        System.out.println("Displaying harvest...");
        SceneHelper.<AddBaseController>addNode((Pane)parentHolder.lookup("#placeHolderAnchorPane"),Nodes.ADDBASE, new AddBaseController(new Harvest()));
    }
}
