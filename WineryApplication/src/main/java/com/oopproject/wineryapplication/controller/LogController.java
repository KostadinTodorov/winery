package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.Nodes;
import com.oopproject.wineryapplication.SceneHelper;
import com.oopproject.wineryapplication.Scenes;
import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    public TextField employeeEmail;
    @FXML
    public AnchorPane base;

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
            employeeName.setText("");
            password.setText("");
            employeeEmail.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong credentials!");
            alert.setContentText("Make sure you have entered the correct credentials!");
            alert.showAndWait();
        }
//        SceneHelper.<AddBaseController>addNode(base,Nodes.ADDBASE, new AddBaseController(new Employee()));
        SceneHelper.<DisplayBaseController>switchTo(Scenes.DISPLAYBASE, new DisplayBaseController(Person.class));
    }
}
