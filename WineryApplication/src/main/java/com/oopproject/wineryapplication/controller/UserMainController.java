package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.data.User;
import com.oopproject.wineryapplication.helpers.ButtonsHelper;
import com.oopproject.wineryapplication.helpers.ButtonsMapHolder;
import com.oopproject.wineryapplication.helpers.ButtonsMappingRegisters;
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
        btnLogOut.setOnAction(e -> {User.userLogout();});

        if(User.getEmployeeOccupationBasedOnWellcome().equals("ceo")){

            System.out.println("CEO entered");
            lblUserGreeting.setText(String.format("# %s : Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
            ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.CEO, placeHolderVBox, ButtonsMapHolder.actionMap);

        } else if (User.getEmployeeOccupationBasedOnWellcome().equals("storage organiser")) {

            System.out.println("Storage organiser entered");
            lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
            ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.STORAGEORGANISER, placeHolderVBox, ButtonsMapHolder.actionMap);

        } else if (User.getEmployeeOccupationBasedOnWellcome().equals("accountant")) {

            System.out.println("Accountant entered");
            lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
            ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.ACCOUNTANT, placeHolderVBox, ButtonsMapHolder.actionMap);

        } else if (User.getEmployeeOccupationBasedOnWellcome().equals("devision lead")) {

            System.out.println("Devision Lead entered");
            lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(),User.getEmployee().getPerson().getPersonName()));
            ButtonsHelper.setButtonsFor(ButtonsMappingRegisters.DEVISIONLEAD, placeHolderVBox, ButtonsMapHolder.actionMap);

        }
    }

}
