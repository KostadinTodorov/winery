package com.oopproject.wineryapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitchingController {

   @FXML
   private Stage stage;
   @FXML
   private Scene scene;
   @FXML
   private AnchorPane placeHolderAnchPane;

    @FXML
    protected void switchToStorageOrganiserMain(ActionEvent event) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("StorageOrganiserMain.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(fxmlLoader.load());
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    protected void switchToBottledWineInventory(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("BottledWineInventory.fxml"));
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        placeHolderAnchPane.getChildren().setAll(pane);
    }
}