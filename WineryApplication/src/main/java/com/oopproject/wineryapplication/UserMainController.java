package com.oopproject.wineryapplication;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserMainController {

    @FXML
    private VBox placeHolderVBox;
    @FXML
    private AnchorPane placeHolderAnchorPane;

    @FXML
    public void initialize()
    {
        // TODO: Just an example
        ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.CEO, placeHolderVBox);

        // TODO: add If statement, which checks the User and decides which buttons to be loaded
        //ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.CEO, placeHolderVBox);
        //ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.CEO, placeHolderVBox);
        //ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.CEO, placeHolderVBox);
    }

}
