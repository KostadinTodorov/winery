package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.*;
import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.managment.orders.OrderRequirements;
import com.oopproject.wineryapplication.managment.orders.ProcessOrder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class OperationsController {

    @FXML
    private Button selectOrderButton;
    @FXML
    private VBox operationsVbox;
    @FXML
    private TextField orderNameTextField;

    private String title;

    public OperationsController(String title) {
        this.title = title;
    }

    @FXML
    private void initialize() {
        this.selectOrderButton.setOnAction(event -> {handleSelectOrderButtonAction();});
    }

    @FXML
    private void handleSelectOrderButtonAction() {
        Integer orderId;
        try {
            orderId = Integer.parseInt(orderNameTextField.getText());
        } catch (NumberFormatException e) {
            orderId = -1; // Default valuе
            System.err.println("Invalid number format: " + orderNameTextField.getText());
        }

        try {
            switch (this.title.toUpperCase().replaceAll("\\s", "")){
                case "ORDERS" ->{

                    ClientsOrderDao clientsOrder = new ClientsOrderDao();
                    ProcessOrder processOrder = new ProcessOrder(clientsOrder.get(orderId));
                    OrderRequirements orderRequirements = processOrder.getOrderRequirements();
                   // Map<Sort, Float> sortRatioMap;
                   // sortRatioMap
                   // List<Harvest> harvestList = orderRequirements.harvestsNeededForOrder();



                    if(/*harvestList.size() > 0*/ true){
                        Label orderLabel1 = new Label(String.format("Order # %d requires:\n", orderId));
                        Label orderLabel2 = new Label(String.format("\t- \n", orderId));
                        Label orderLabel3 = new Label(String.format("\t- \n", orderId));
                        Label orderLabel4 = new Label(String.format("\t- \n", orderId));

                        operationsVbox.getChildren().addAll(orderLabel1, orderLabel2, orderLabel3, orderLabel4);
                    }else {
                        Button setOrderStatusButton = new Button("Set Order Status");
                        ProgressDao orderStatusDao = new ProgressDao();
                        Progress progress = orderStatusDao.get(2);
                        setOrderStatusButton.setOnAction(event -> {processOrder.updateOrder(progress);
                                                                              processOrder.completeOrder(LocalDate.now());});

                        operationsVbox.getChildren().add(new Label("No order of Harvest is needed! The Clients order can be set to done!"));
                        operationsVbox.getChildren().add(setOrderStatusButton);
                    }


                }
                case "PRODUCTION" ->{
                    Batch batch = new BatchDao().get(orderId);
                    Sweetness sweetness = new SweetnessDao().get(orderId);
                    BottleType bottleType = new BottleTypeDao().get(orderId);

                   // this.operationClass = ProcessOrder.class;
                   // this.operationInstance = this.operationClass.getDeclaredConstructor(ClientsOrder.class).newInstance(batch, sweetness, 12, 100, bottleType);
                   // if (operationInstance instanceof ProcessOrder processOrder) {
                   //     processOrder.testdisplay();
                   // } else {
                   //     LoggerHelper.logData(OperationsController.class, LoggerLevels.ERROR, "Issue with process PRODUCTION");
                   // }
                }
            }
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }

    }
}
