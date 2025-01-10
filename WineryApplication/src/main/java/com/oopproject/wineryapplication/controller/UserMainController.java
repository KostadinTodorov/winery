package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.Role;
import com.oopproject.wineryapplication.helper.Nodes;
import com.oopproject.wineryapplication.helper.SceneHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserMainController {


    @FXML
    private AnchorPane placeHolderAnchPane;
    @FXML
    private VBox buttonContainer;

    private void setRole(Role role) {
        buttonContainer.getChildren().clear();
        role.getRoleActions().forEach((actionName, handler) -> {
            Button button = new Button(actionName);
            button.setOnAction(e -> handler.run());
            buttonContainer.getChildren().add(button);
        });
    }

    @FXML
    public void initialize()
    {
        Role adminRole = new Role("Admin");
        adminRole.addRoleAction("Manage Users", () -> System.out.println("Managing users..."));
        adminRole.addRoleAction("View Reports", () -> System.out.println("Viewing reports..."));
        adminRole.addRoleAction("View Reports", () -> System.out.println("Viewing reports..."));
        adminRole.addRoleAction("Download Data", () -> System.out.println("Downloading data..."));

        // Example: Load buttons for admin role
        setRole(adminRole);
    }

    @FXML
    protected void switchToBottledWineInventory(ActionEvent event) throws IOException {
        SceneHelper.addNode(placeHolderAnchPane, Nodes.BOTTLEDWINEINVENTORY);
    }
}
