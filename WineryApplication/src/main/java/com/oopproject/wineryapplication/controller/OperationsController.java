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

/**
 * Този клас представлява контролер, който обработва функционалности, свързани с
 * управление на операции. Използва се за обработка на потребителски действия и
 * управление на данни, необходими за изпълнението на специфични операции, свързани
 * с поръчки или производство.
 */
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

    /**
     * Конструкторът {@code OperationsController} създава нов обект
     * с предоставеното заглавие. Заглавието служи за контекстуална
     * дефиниция на операциите, които ще бъдат обработвани от този
     * контролер.
     *
     * @param title Заглавие, което определя контекста на операциите
     *              (например "ORDERS" или "PRODUCTION"). Използва се
     *              за ръководене на логиката в методите на класа.
     */
    public OperationsController(String title) {
        this.title = title;
    }

    @FXML
    private void initialize() {
        this.selectOrderButton.setOnAction(event -> {handleSelectOrderButtonAction();});
    }

    /**
     * Методът {@code handleSelectOrderButtonAction} се използва за обработка на действие
     * при натискане на бутон за избор на поръчка. Той извлича въведения от потребителя
     * идентификатор на поръчка и изпълнява различни логики в зависимост от контекста
     * на заглавието на контролера. Възможните контексти са "ORDERS" и "PRODUCTION".
     *
     * <p>Следващите стъпки са изпълнени в метода:
     * <ul>
     *     <li>Проверява и преобразува въведения от текстовото поле идентификатор на поръчка
     *     в числова стойност. При грешно форматиране се задава стойност по подразбиране.</li>
     *     <li>Въз основа на заглавието се извършва различна обработка:
     *         <ul>
     *             <li>За "ORDERS" идентификатора се използва за извличане на обекта {@link ClientsOrder},
     *             като след това се създава инстанция на {@link ProcessOrder} за обработка на поръчката.</li>
     *             <li>За "PRODUCTION" идентификатора се използва за извличане на обекти от
     *             класовете {@link Batch}, {@link Sweetness} и {@link BottleType}, с които се инициира
     *             създаването на нова инстанция на {@link ProcessOrder} за обработка на производството.</li>
     *         </ul>
     *     </li>
     *     <li>Проверява се дали създадената инстанция е валидна и изпълнява тестови дисплей методи.</li>
     * </ul>
     *
     * <p>Методът обработва изключения като {@link InstantiationException},
     * {@link IllegalAccessException}, {@link InvocationTargetException},
     * {@link NoSuchMethodException} и {@link NumberFormatException}, като в тези случаи
     * изключението или се изхвърля отново, или се задава стойност по подразбиране.</p>
     */
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
                      //  processOrder.testdisplay();
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
