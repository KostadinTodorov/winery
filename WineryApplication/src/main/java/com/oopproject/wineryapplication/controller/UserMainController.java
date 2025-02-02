package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.data.User;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsHelper;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsMapHolderForEachCategory;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsMapHolderForEachOperation;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsMappingRegisters;
import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * The UserMainController class acts as the main controller in a graphical user interface
 * environment. It manages and initializes the user-specific functionality, including
 * greeting messages, action buttons, and user interactions based on the user's occupation
 * within the system. Occupations dictate the visibility and availability of various categories
 * and operations buttons using mappings.
 *
 * This class uses the JavaFX framework and provides functionality for initializing the
 * interface elements and maintaining user interaction logic. Logging is utilized to trace
 * user activity and system states during operation.
 */
public class UserMainController {

    @FXML
    private VBox placeHolderVBoxCat;
    @FXML
    private VBox placeHolderVBoxNtf;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label lblUserGreeting;
    @FXML
    private Label lblUserPrompt;

    /**
     * Initializes the UserMainController with the necessary setup for user interaction
     * based on the user's role.
     *
     * This method is triggered upon the application loading the associated UserMainController
     * FXML. It performs the following configurations:
     * - Logs the initialization of the controller.
     * - Sets up category and operation button mappings.
     * - Displays a prompt message instructing the user to choose a category and an attribute.
     * - Sets the action for the logout button, allowing the user to log out.
     * - Dynamically adjusts UI elements, such as greeting messages and button displays,
     *   according to the user's role (e.g., CEO, storage organiser, accountant, division lead).
     * - Logs the start of the work session for the user, specifying their role and name.
     *
     * The method uses helper classes and methods to handle button generation and mappings,
     * making the interface tailored to the logged-in user's responsibilities.
     */
    @FXML
    public void initialize()
    {
        LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, "Initializing User Controller");

        ButtonsMapHolderForEachCategory categoriesMap = new ButtonsMapHolderForEachCategory();
        ButtonsMapHolderForEachOperation notificationsMap = new ButtonsMapHolderForEachOperation();
        lblUserPrompt.setText ("Please choose a category and then select one of its attributes!");
        btnLogOut.setOnAction(e -> {User.userLogout();});

        switch (User.getEmployeeOccupationBasedOnWellcome()) {
            case "ceo" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s : Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATCEO, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.OPCEO, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
            case "storage organiser" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATSTORAGEORGANISER, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.OPSTORAGEORGANISER, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
            case "accountant" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATACCOUNTANT, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.OPACCOUNTANT, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
            case "devision lead" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATDEVISIONLEAD, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.OPDEVISIONLEAD, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
        }
    }

}
