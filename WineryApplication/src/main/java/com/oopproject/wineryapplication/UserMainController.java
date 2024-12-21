package com.oopproject.wineryapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
