package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.data.User;
import com.oopproject.wineryapplication.helpers.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserMainController {

    @FXML
    private VBox placeHolderVBox;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label lblUserGreeting;

    @FXML
    public void initialize()
    {
        LoggerHelper.logData(UserMainController.class, LoggerLevels.DEBUG, "Initializing User Controller");
        btnLogOut.setOnAction(e -> {User.userLogout();});

        if(User.getEmployeeOccupationBasedOnWellcome().equals("ceo")){

            //System.out.println("CEO entered");
            LoggerHelper.logData(UserMainController.class, LoggerLevels.DEBUG, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

            lblUserGreeting.setText(String.format("# %s : Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
            ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.CEO, placeHolderVBox, ButtonsMapHolder.actionMap);

        } else if (User.getEmployeeOccupationBasedOnWellcome().equals("storage organiser")) {

            //System.out.println("Storage organiser entered");
            LoggerHelper.logData(UserMainController.class, LoggerLevels.DEBUG, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

            lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
            ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.STORAGEORGANISER, placeHolderVBox, ButtonsMapHolder.actionMap);

        } else if (User.getEmployeeOccupationBasedOnWellcome().equals("accountant")) {

            //System.out.println("Accountant entered");
            LoggerHelper.logData(UserMainController.class, LoggerLevels.DEBUG, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

            lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
            ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.ACCOUNTANT, placeHolderVBox, ButtonsMapHolder.actionMap);

        } else if (User.getEmployeeOccupationBasedOnWellcome().equals("devision lead")) {

            //System.out.println("Devision Lead entered");
            LoggerHelper.logData(UserMainController.class, LoggerLevels.DEBUG, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

            lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(),User.getEmployee().getPerson().getPersonName()));
            ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.DEVISIONLEAD, placeHolderVBox, ButtonsMapHolder.actionMap);

        }
    }

}
