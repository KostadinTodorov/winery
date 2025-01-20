package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.helpers.SceneHelper;
import com.oopproject.wineryapplication.helpers.Scenes;
import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

            emp = User.CheckEmployee(employeeName.getText(),password.getText());
            //System.out.println(emp.getPerson().getPersonName());
            if (emp != null) {
                System.out.println(String.format("Hello, %s!\nYou enter as %s user.", emp.getPerson().getPersonName(), emp.getOccupation().getOccupation()));

                SceneHelper.switchTo(Scenes.USER);
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new NullPointerException(e.getMessage());
        }

    }

}
