package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.data.User;
import com.oopproject.wineryapplication.helpers.LoggerHelper;
import com.oopproject.wineryapplication.helpers.LoggerLevels;
import com.oopproject.wineryapplication.helpers.SceneHelper;
import com.oopproject.wineryapplication.helpers.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class WelcomeController {

    @FXML
    Button btnCEO;
    @FXML
    Button btnStorageOrganiser;
    @FXML
    Button btnAccountant;
    @FXML
    Button btnDevisionLead;

    @FXML
    public void initialize() {
        LoggerHelper.logData(WelcomeController.class, LoggerLevels.DEBUG, "Initialize Welcome Controller");
    }

    @FXML
    protected void switchToLogin(ActionEvent event) throws IOException {

        Object source = event.getSource();

        if(btnCEO == source){

            //System.out.println("CEO pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.DEBUG, "CEO pressed");
            User.setEmployeeOccupationBasedOnWelcome("ceo");

        } else if (btnStorageOrganiser == source) {

            //System.out.println("Storage organiser pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.DEBUG, "Storage organiser pressed");
            User.setEmployeeOccupationBasedOnWelcome("storage organiser");

        } else if (btnAccountant == source) {

            //System.out.println("Accountant pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.DEBUG, "Accountant pressed");
            User.setEmployeeOccupationBasedOnWelcome("accountant");

        } else if (btnDevisionLead == source) {

            //System.out.println("Devision Lead pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.DEBUG, "Devision Lead pressed");
            User.setEmployeeOccupationBasedOnWelcome("devision lead");

        }

        SceneHelper.switchTo(Scenes.LOG);
    }

}
