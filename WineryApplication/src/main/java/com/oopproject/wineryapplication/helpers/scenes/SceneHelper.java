package com.oopproject.wineryapplication.helpers.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SceneHelper
{

    private static Scene scene;

    public static void setScene(Scene scene)
    {
        SceneHelper.scene = scene;
    }

    public static void switchTo(Scenes selectedScene)
    {
        if (SceneHelper.scene == null)
        {
        System.out.println("No scene is being declared!");
        }

        try {

            Parent rootNode = FXMLLoader.load(SceneHelper.class.getResource(selectedScene.getFileName()));
            SceneHelper.scene.setRoot(rootNode);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static <T> void switchTo (Scenes selectedScene, T controller) {
        if (SceneHelper.scene == null)
        {
            System.out.println("No scene is being declared!");
        }

        try {
            FXMLLoader loader = new FXMLLoader(SceneHelper.class.getResource(selectedScene.getFileName()));
            loader.setController(controller);
            SceneHelper.scene.setRoot(loader.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void addNode(Pane paneNode, Nodes node)
    {
        if (SceneHelper.scene == null)
        {
            System.out.println("No scene is being declared!");
        }

        try {

            AnchorPane newNode = FXMLLoader.load(SceneHelper.class.getResource(node.getFileName()));
            AnchorPane.setLeftAnchor(newNode, 0.0);
            AnchorPane.setBottomAnchor(newNode, 0.0);
            AnchorPane.setRightAnchor(newNode, 0.0);
            AnchorPane.setTopAnchor(newNode, 0.0);
            paneNode.getChildren().setAll(newNode);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void addNode(Pane paneNode, Nodes node, T controller)
    {
        if (SceneHelper.scene == null)
        {
            System.out.println("No scene is being declared!");
        }

        try {
            FXMLLoader loader = new FXMLLoader(SceneHelper.class.getResource(node.getFileName()));
            loader.setController(controller);
            AnchorPane newNode = loader.load();
            AnchorPane.setLeftAnchor(newNode, 0.0);
            AnchorPane.setBottomAnchor(newNode, 0.0);
            AnchorPane.setRightAnchor(newNode, 0.0);
            AnchorPane.setTopAnchor(newNode, 0.0);
            paneNode.getChildren().setAll(newNode);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}