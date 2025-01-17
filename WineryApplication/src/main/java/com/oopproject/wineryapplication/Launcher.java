package com.oopproject.wineryapplication;

import com.oopproject.wineryapplication.helpers.SceneHelper;
import com.oopproject.wineryapplication.helpers.Scenes;
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
       launch();
    }
}