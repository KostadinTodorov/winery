package com.oopproject.wineryapplication.helpers;

import com.oopproject.wineryapplication.access.entities.ClientsOrder;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.entities.Harvest;
import com.oopproject.wineryapplication.access.entities.WineType;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import com.oopproject.wineryapplication.controller.AddBaseController;
import com.oopproject.wineryapplication.controller.DisplayBaseController;
import jakarta.persistence.criteria.Order;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ButtonsHelper {



    public static void setButtonsFor(ButtonsMappingRegisters actionBitmap, VBox vboxHolder) {
        Integer actionInstance = actionBitmap.getButtonsMapping();
        String[] predefinedActions = {"Workers", "Wine", "Harvest", "Orders"};
        Runnable[] predefinedHandlers = {
                () -> {
                    try {
                        displayEntity( (BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent(), new Employee() );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        displayEntity( (BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent(), new WineType() );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        displayEntity( (BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent(), new Harvest() );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        displayEntity( (BorderPane) ((AnchorPane)vboxHolder.getParent()).getParent(), new ClientsOrder() );
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

    protected static void displayEntity(BorderPane parentHolder, Entity entity) throws IOException {
        System.out.println("Displaying Entity = ");
        SceneHelper.<DisplayBaseController>addNode((Pane)parentHolder.lookup("#placeHolderAnchorPane"),Nodes.DISPLAYBASE, new DisplayBaseController(entity.getClass()));
    }
}
