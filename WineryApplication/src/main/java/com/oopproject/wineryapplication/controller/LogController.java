package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.helpers.SceneHelper;
import com.oopproject.wineryapplication.helpers.Scenes;
import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogController {

    @FXML
    public PasswordField password;
    @FXML
    public TextField employeeName;
    @FXML
    public AnchorPane base;

    @FXML
    protected void switchToUser(ActionEvent event) throws IOException {

        Employee emp;
        try {

            emp = User.GetInstance(employeeName.getText(),password.getText());
            System.out.println(emp.getPerson().getPersonName());


            SceneHelper.switchTo(Scenes.USER);
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }

    }

}
