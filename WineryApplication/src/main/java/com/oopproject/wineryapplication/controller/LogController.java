package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.Nodes;
import com.oopproject.wineryapplication.SceneHelper;
import com.oopproject.wineryapplication.Scenes;
import com.oopproject.wineryapplication.access.entities.Act;
import com.oopproject.wineryapplication.access.entities.Batch;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LogController {

    public PasswordField password;
    public TextField employeeName;
    public TextField employeeEmail;

    @FXML
    private AnchorPane placeHolderAnchPane;

    @FXML
    protected void switchToUser(ActionEvent event) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("User.fxml"));
       // stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       // scene = new Scene(fxmlLoader.load());
       // stage.setScene(scene);
       // stage.show();

        // TODO: add a passing point

        SceneHelper.switchTo(Scenes.USER);
    }

    @FXML
    protected void switchToBottledWineInventory(ActionEvent event) throws IOException {
        Employee emp;
        try {
//            emp = User.GetInstance("Joe Biden", "letsgobranden@mail.com", "46");
            emp = User.GetInstance(Integer.parseInt(employeeName.getText()),password.getText());
            System.out.println(emp.getPerson().getPersonName());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("Cause: " + e.getCause());
        }
        SceneHelper.<AddBaseController>switchTo(Scenes.ADDBASE, new AddBaseController(new Employee()));
    }
}
