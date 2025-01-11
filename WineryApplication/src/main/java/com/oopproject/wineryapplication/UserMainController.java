package com.oopproject.wineryapplication;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserMainController {


    @FXML
    private AnchorPane placeHolderAnchPane;
    @FXML
    private VBox placeHolderVBox;

    @FXML
    public void initialize()
    {
        UserButtonsHelper.setUserWithBitmap(placeHolderVBox ,UserButtonsMapping.CEO);
    }

}
