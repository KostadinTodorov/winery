package com.oopproject.wineryapplication;

import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import com.oopproject.wineryapplication.helpers.scenes.SceneHelper;
import com.oopproject.wineryapplication.helpers.scenes.Scenes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane());
        SceneHelper.setScene(scene);
        SceneHelper.switchTo(Scenes.WELLCOME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
       LoggerHelper.logData(Launcher.class, LoggerLevels.INFO, "Launch the Winery Application");
       launch();
    }
}