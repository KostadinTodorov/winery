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

public class LogController {

    @FXML
    public PasswordField password;
    @FXML
    public TextField employeeName;
    @FXML
    public Label lblEnterCredentials;
    @FXML
    public Button btnGoBack;


    @FXML
    public void initialize() {
        LoggerHelper.logData(LogController.class, LoggerLevels.INFO, "Initialize Log Controller");

        btnGoBack.setOnAction((ActionEvent event) -> {User.userLogout();});
        lblEnterCredentials.setText(String.format("Enter %s credentials:", User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));
    }

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
