package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.data.User;
import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import com.oopproject.wineryapplication.helpers.scenes.SceneHelper;
import com.oopproject.wineryapplication.helpers.scenes.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * The WelcomeController class acts as the controller for the Welcome scene in a JavaFX application.
 * It handles interactions with the UI elements present in the Welcome screen, including button presses
 * for different user roles.
 */
public class WelcomeController {

    @FXML
    Button btnCEO;
    @FXML
    Button btnStorageOrganiser;
    @FXML
    Button btnAccountant;
    @FXML
    Button btnDevisionLead;

    /**
     * Initializes the WelcomeController by performing any required setup tasks
     * before the controller is used. This method is automatically called after
     * the FXML file has been loaded and ensures that the controller's state is initialized.
     *
     * Functionality:
     * - Logs an informational message indicating the initialization of the
     *   WelcomeController using the LoggerHelper utility.
     */
    @FXML
    public void initialize() {
        LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "Initialize Welcome Controller");
    }

    /**
     * Handles the action of switching to the login screen based on the button pressed.
     * Updates the user's occupation in the system depending on the selected role and
     * transitions to the login scene.
     *
     * @param event The ActionEvent triggered by pressing one of the role selection buttons
     *              (e.g., CEO, Storage Organiser, Accountant, or Division Lead).
     * @throws IOException if an I/O error occurs during the scene transition.
     */
    @FXML
    protected void switchToLogin(ActionEvent event) throws IOException {

        Object source = event.getSource();

        if(btnCEO == source){

            //System.out.println("CEO pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "CEO pressed");
            User.setEmployeeOccupationBasedOnWelcome("ceo");

        } else if (btnStorageOrganiser == source) {

            //System.out.println("Storage organiser pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "Storage organiser pressed");
            User.setEmployeeOccupationBasedOnWelcome("storage organiser");

        } else if (btnAccountant == source) {

            //System.out.println("Accountant pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "Accountant pressed");
            User.setEmployeeOccupationBasedOnWelcome("accountant");

        } else if (btnDevisionLead == source) {

            //System.out.println("Devision Lead pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "Devision Lead pressed");
            User.setEmployeeOccupationBasedOnWelcome("devision lead");

        }

        SceneHelper.switchTo(Scenes.LOG);
    }

}
