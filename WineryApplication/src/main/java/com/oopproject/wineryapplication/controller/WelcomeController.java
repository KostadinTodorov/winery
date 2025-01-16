package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.helpers.SceneHelper;
import com.oopproject.wineryapplication.helpers.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class WelcomeController {

    @FXML
    protected void switchToLogin(ActionEvent event) throws IOException {

        SceneHelper.switchTo(Scenes.LOG);
    }

}
