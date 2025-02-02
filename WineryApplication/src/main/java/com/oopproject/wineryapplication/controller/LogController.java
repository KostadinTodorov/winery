package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import com.oopproject.wineryapplication.helpers.scenes.SceneHelper;
import com.oopproject.wineryapplication.helpers.scenes.Scenes;
import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * The LogController class is a JavaFX controller responsible for managing interactions
 * with the login screen of the application. It handles user input, navigation between
 * scenes, and logging user actions for monitoring purposes.
 *
 * This class facilitates the following:
 * - Accepts user input for employee credentials (employee name and password).
 * - Provides feedback messages to users for actions or errors.
 * - Handles user logout and transitions between screens.
 * - Logs significant user actions using the LoggerHelper utility.
 */
public class LogController {

    @FXML
    public PasswordField password;
    @FXML
    public TextField employeeName;
    @FXML
    public Label lblEnterCredentials;
    @FXML
    public Button btnGoBack;


    /**
     * Initializes the LogController by setting up the initial configuration for the UI elements
     * and logging the initialization event.
     *
     * Functionality:
     * - Logs the initialization message with an INFO log level.
     * - Configures the "Go Back" button to handle user logout.
     * - Dynamically sets the label text prompting the user to enter credentials
     *   based on the current employee occupation retrieved from the User class.
     */
    @FXML
    public void initialize() {
        LoggerHelper.logData(LogController.class, LoggerLevels.INFO, "Initialize Log Controller");

        btnGoBack.setOnAction((ActionEvent event) -> {User.userLogout();});
        lblEnterCredentials.setText(String.format("Enter %s credentials:", User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));
    }

    /**
     * Handles the user login process by verifying employee credentials and switching
     * the application scene for valid users. This method is invoked when a user attempts
     * to access the user-specific functionality by providing their username and password.
     *
     * Functionality:
     * - Retrieves and validates employee credentials (employee name and password) provided via text input fields.
     * - Uses the User class to verify the validity of the entered credentials and fetches the associated Employee entity.
     * - Logs the operation if the credentials are valid, including the employee's username and occupation.
     * - Switches the application’s UI to the "USER" scene upon successful login.
     *
     * Exception Handling:
     * - If a NullPointerException is encountered (e.g., missing or null inputs), logs the exception message
     *   and rethrows it for further handling.
     *
     * @throws IOException if an input or output error occurs during scene transition.
     */
    @FXML
    protected void switchToUser() throws IOException {

        Employee emp;
        try {

            emp = User.CheckEmployee(employeeName.getText(),password.getText());
            if (emp != null) {
                LoggerHelper.logData(LogController.class, LoggerLevels.INFO, String.format("%s enters as %s user.", emp.getPerson().getPersonName(), emp.getOccupation().getOccupation()));

                SceneHelper.switchTo(Scenes.USER);
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new NullPointerException(e.getMessage());
        }

    }
}
