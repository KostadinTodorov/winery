package com.oopproject.wineryapplication.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
public class ButtonController {
    @FXML
    private VBox buttonContainer;

    public void initialize() {
        for (ButtonType buttonType : ButtonType.values()) {
            Button button = new Button(buttonType.getLabel());
            button.setMaxWidth(Double.MAX_VALUE); // Make buttons stretch to fill available width
            button.setOnAction(event -> handleButtonAction(buttonType)); // Set action handler
            buttonContainer.getChildren().add(button);
        }
    }

    private void handleButtonAction(ButtonType buttonType) {
        System.out.println("Button clicked: " + buttonType.getLabel());
        // Add further handling logic here
    }
}
