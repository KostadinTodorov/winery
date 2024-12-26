package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.helper.Nodes;
import com.oopproject.wineryapplication.helper.SceneHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserMainController {


    @FXML
    private AnchorPane placeHolderAnchPane;


    @FXML
    protected void switchToBottledWineInventory(ActionEvent event) throws IOException {
        SceneHelper.addNode(placeHolderAnchPane, Nodes.BOTTLEDWINEINVENTORY);
    }
}
