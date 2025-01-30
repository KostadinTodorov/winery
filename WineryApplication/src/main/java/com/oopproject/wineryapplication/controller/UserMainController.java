package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.data.User;
import com.oopproject.wineryapplication.helpers.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserMainController {

    @FXML
    private VBox placeHolderVBox;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label lblUserGreeting;
    @FXML
    private Label lblUserPrompt;
    @FXML
    private AnchorPane placeHolderAnchorPane;

    @FXML
    public void initialize()
    {
        LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, "Initializing User Controller");

        ButtonsMapHolderForEachCategory categoriesMap = new ButtonsMapHolderForEachCategory();
        lblUserPrompt.setText ("Please choose a category and then select one of its attributes!");
        btnLogOut.setOnAction(e -> {User.userLogout();});

        switch (User.getEmployeeOccupationBasedOnWellcome()) {
            case "ceo" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s : Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.CEO, placeHolderVBox, categoriesMap.getActionMap());
            }
            case "storage organiser" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.STORAGEORGANISER, placeHolderVBox, categoriesMap.getActionMap());
            }
            case "accountant" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.ACCOUNTANT, placeHolderVBox, categoriesMap.getActionMap());
            }
            case "devision lead" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.DEVISIONLEAD, placeHolderVBox, categoriesMap.getActionMap());
            }
        }
    }

}
