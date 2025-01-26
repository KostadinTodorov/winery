package com.oopproject.wineryapplication;

import com.oopproject.wineryapplication.helpers.SceneHelper;
import com.oopproject.wineryapplication.helpers.Scenes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Launcher extends Application {

    private static final Logger logger = LogManager.getLogger(Launcher.class);

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane());
        SceneHelper.setScene(scene);
        SceneHelper.switchTo(Scenes.WELLCOME);
        stage.setScene(scene);
        stage.show();

        Launcher.logger.info("Start of Winery Applsdcsdcsdcdscdscsdcsdcdscdsication");
        System.out.println("Welcome to Winery Applicatidscdvdecedcedcececcccccccccon");
    }

    public static void main(String[] args) {
       launch();
    }
}