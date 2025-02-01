package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.data.User;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsHelper;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsMapHolderForEachCategory;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsMapHolderForEachNotification;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsMappingRegisters;
import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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

    @FXML
    public void initialize()
    {
        LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, "Initializing User Controller");

        ButtonsMapHolderForEachCategory categoriesMap = new ButtonsMapHolderForEachCategory();
        ButtonsMapHolderForEachNotification notificationsMap = new ButtonsMapHolderForEachNotification();
        lblUserPrompt.setText ("Please choose a category and then select one of its attributes!");
        btnLogOut.setOnAction(e -> {User.userLogout();});

        switch (User.getEmployeeOccupationBasedOnWellcome()) {
            case "ceo" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s : Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATCEO, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.NTFCEO, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
            case "storage organiser" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATSTORAGEORGANISER, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.NTFSTORAGEORGANISER, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
            case "accountant" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATACCOUNTANT, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.NTFACCOUNTANT, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
            case "devision lead" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATDEVISIONLEAD, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.NTFDEVISIONLEAD, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
        }
    }

}
