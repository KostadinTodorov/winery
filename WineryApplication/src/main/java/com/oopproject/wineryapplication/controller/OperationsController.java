package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.access.daos.BatchDao;
import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ClientsOrderDao;
import com.oopproject.wineryapplication.access.daos.SweetnessDao;
import com.oopproject.wineryapplication.access.entities.Batch;
import com.oopproject.wineryapplication.access.entities.BottleType;
import com.oopproject.wineryapplication.access.entities.ClientsOrder;
import com.oopproject.wineryapplication.access.entities.Sweetness;
import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import com.oopproject.wineryapplication.managment.orders.ProcessOrder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.lang.reflect.InvocationTargetException;

public class OperationsController {

    @FXML
    private Button selectOrderButton;
    @FXML
    private VBox operationsPane;
    @FXML
    private TextField orderNameTextField;

    private String title;
    private Class <?> operationClass;
    private Object operationInstance; // Store instance

    public OperationsController(String title) {
        this.title = title;
    }

    @FXML
    private void initialize() {
        this.selectOrderButton.setOnAction(event -> {handleSelectOrderButtonAction();});
    }

    @FXML
    private void handleSelectOrderButtonAction() {
        int orderId;
        String selectedOrderId = orderNameTextField.getText();
        try {
            orderId = Integer.parseInt(selectedOrderId);
        } catch (NumberFormatException e) {
            orderId = 999999; // Default valuе
            System.err.println("Invalid number format: " + selectedOrderId);
        }

        try {
            switch (this.title.toUpperCase().replaceAll("\\s", "")){
                case "ORDERS" ->{
                    ClientsOrder clientsOrder = new ClientsOrderDao().get(orderId);

                    this.operationClass = ProcessOrder.class;
                    this.operationInstance = this.operationClass.getDeclaredConstructor(ClientsOrder.class).newInstance(clientsOrder);
                    if (operationInstance instanceof ProcessOrder processOrder) {
                        processOrder.testdisplay();
                    } else {
                        LoggerHelper.logData(OperationsController.class, LoggerLevels.ERROR, "Issue with process ORDER");
                    }
                }
                case "PRODUCTION" ->{
                    Batch batch = new BatchDao().get(orderId);
                    Sweetness sweetness = new SweetnessDao().get(orderId);
                    BottleType bottleType = new BottleTypeDao().get(orderId);

                    this.operationClass = ProcessOrder.class;
                    this.operationInstance = this.operationClass.getDeclaredConstructor(ClientsOrder.class).newInstance(batch, sweetness, 12, 100, bottleType);
                    if (operationInstance instanceof ProcessOrder processOrder) {
                        processOrder.testdisplay();
                    } else {
                        LoggerHelper.logData(OperationsController.class, LoggerLevels.ERROR, "Issue with process PRODUCTION");
                    }
                }
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
