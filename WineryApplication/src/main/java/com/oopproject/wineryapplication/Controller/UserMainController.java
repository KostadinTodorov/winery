package com.oopproject.wineryapplication.Controller;

import com.oopproject.wineryapplication.Nodes;
import com.oopproject.wineryapplication.SceneHelper;
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
