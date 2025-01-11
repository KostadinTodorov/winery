package com.oopproject.wineryapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class WelcomeController {

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
    protected void switchToLogin(ActionEvent event) throws IOException {
        SceneHelper.switchTo(Scenes.LOG);
    }

}
