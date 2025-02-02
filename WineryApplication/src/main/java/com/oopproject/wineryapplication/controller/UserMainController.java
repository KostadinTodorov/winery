package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.data.User;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsHelper;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsMapHolderForEachCategory;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsMapHolderForEachOperation;
import com.oopproject.wineryapplication.helpers.buttons.ButtonsMappingRegisters;
import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * {@code UserMainController} е клас, който управлява основния потребителски изглед в приложението.
 *
 * Този контролер работи с JavaFX елементи, за да настрои интерфейса въз основа на ролята на потребителя
 * и предоставя необходимата функционалност, за да се извършват специфични операции.
 */
public class UserMainController {

    @FXML
    private VBox placeHolderVBoxCat;
    @FXML
    private VBox placeHolderVBoxNtf;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label lblUserGreeting;
    @FXML
    private Label lblUserPrompt;

    /**
     * Методът {@code initialize} се използва за инициализация на потребителския контролер
     * и настройка на различните данни и елементи в интерфейса според длъжността на потребителя.
     * Извиква се автоматично посредством анотацията {@code @FXML}.
     *
     * <p>Методът изпълнява следните действия:
     * <ul>
     *     <li>Записва лог за инициализацията на потребителския контролер с помощта на
     *         {@link LoggerHelper#logData(Class, LoggerLevels, String)}.</li>
     *     <li>Създава екземпляри на {@link ButtonsMapHolderForEachCategory} и
     *         {@link ButtonsMapHolderForEachOperation}, които държат асоциации на бутони за категории и операции.</li>
     *     <li>Установява текстова информация в потребителския интерфейс - например приветстващ текст
     *         и насоки за потребителите.</li>
     *     <li>Настройва действие за изход с помощта на {@link Button#setOnAction(javafx.event.EventHandler)}
     *         и метода {@link User#userLogout()} за изход от профила на потребителя.</li>
     *     <li>Определя потребителската длъжност чрез {@link User#getEmployeeOccupationBasedOnWellcome()} и
     *         настройва подходящо съдържание в интерфейса на базата на длъжността.</li>
     *     <li>За всяка длъжност зарежда съответните бутони за категории и операции чрез
     *         {@link ButtonsHelper#addButtons}.</li>
     * </ul>
     *
     * @return {@inheritDoc}
     */
    @FXML
    public void initialize()
    {
        LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, "Initializing User Controller");

        ButtonsMapHolderForEachCategory categoriesMap = new ButtonsMapHolderForEachCategory();
        ButtonsMapHolderForEachOperation notificationsMap = new ButtonsMapHolderForEachOperation();
        lblUserPrompt.setText ("Please choose a category and then select one of its attributes!");
        btnLogOut.setOnAction(e -> {User.userLogout();});

        switch (User.getEmployeeOccupationBasedOnWellcome()) {
            case "ceo" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s : Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATCEO, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.OPCEO, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
            case "storage organiser" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATSTORAGEORGANISER, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.OPSTORAGEORGANISER, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
            case "accountant" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATACCOUNTANT, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.OPACCOUNTANT, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
            case "devision lead" -> {

                LoggerHelper.logData(UserMainController.class, LoggerLevels.INFO, String.format("%s ( %s ) begun work", User.getEmployee().getPerson().getPersonName(), User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));

                lblUserGreeting.setText(String.format("# %s Welcome, %s", User.getEmployeeOccupationBasedOnWellcome().toUpperCase(), User.getEmployee().getPerson().getPersonName()));
                ButtonsHelper.addButtons(ButtonsMappingRegisters.CATDEVISIONLEAD, placeHolderVBoxCat, categoriesMap.getActionMap(), true);
                ButtonsHelper.addButtons(ButtonsMappingRegisters.OPDEVISIONLEAD, placeHolderVBoxNtf, notificationsMap.getActionMap(), true);
            }
        }
    }

}
