package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import com.oopproject.wineryapplication.helpers.scenes.SceneHelper;
import com.oopproject.wineryapplication.helpers.scenes.Scenes;
import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Класът {@code LogController} отговаря за контролиране на потребителския интерфейс свързан
 * с влизането на потребители в системата. Той управлява логическите операции като проверка
 * на потребителски данни, промяна на сцени и изписване на информация в логовете.
 */
public class LogController {

    @FXML
    public PasswordField password;
    @FXML
    public TextField employeeName;
    @FXML
    public Label lblEnterCredentials;
    @FXML
    public Button btnGoBack;


    /**
     * Методът {@code initialize} служи за инициализация на графичния компонент {@link LogController}
     * и настройва началните взаимодействия за потребителския интерфейс.
     *
     * Функционалност:
     * - Логва информационно съобщение чрез {@link LoggerHelper#logData(Class, LoggerLevels, String)}, което
     * указва, че инициализацията на контролера е стартирана.
     * - Настройва бутонът за връщане назад ({@code btnGoBack}) да извърши операцията {@link User#userLogout()} при натискане.
     * - Актуализира текста на етикета {@code lblEnterCredentials}, като го форматира с длъжността
     * на потребителя, взета чрез метода {@link User#getEmployeeOccupationBasedOnWellcome()}.
     */
    @FXML
    public void initialize() {
        LoggerHelper.logData(LogController.class, LoggerLevels.INFO, "Initialize Log Controller");

        btnGoBack.setOnAction((ActionEvent event) -> {User.userLogout();});
        lblEnterCredentials.setText(String.format("Enter %s credentials:", User.getEmployeeOccupationBasedOnWellcome().toUpperCase()));
    }

    /**
     * Методът {@code switchToUser} отговаря за проверка на автентичността на въведени потребителски
     * данни и, в случай на успешна проверка, превключва потребителя към сцена, подходяща за неговата роля.
     *
     * Операция:
     * - Извлича въведените данни от полетата {@link LogController#employeeName} и {@link LogController#password}.
     * - Проверява данните чрез метода {@link User#CheckEmployee(String, String)}.
     * - Ако потребителят е валиден ({@code emp != null}), логва съобщение с използването на
     *   {@link LoggerHelper#logData(Class, LoggerLevels, String)}.
     * - При успешна проверка променя сцената към потребителския изглед с помощта на метода {@link SceneHelper#switchTo(Scenes)}.
     *
     * Изключения и обработка:
     * - Методът прихваща {@link NullPointerException} в случай че липсват необходимите потребителски данни
     *   или възникне неочаквано събитие, и го прехвърля нагоре със съхранение на съобщението за грешка.
     *
     * @throws IOException Възниква при грешка в смяната на сцената.
     */
    @FXML
    protected void switchToUser() throws IOException {

        Employee emp;
        try {

            emp = User.CheckEmployee(employeeName.getText(),password.getText());
            if (emp != null) {
                LoggerHelper.logData(LogController.class, LoggerLevels.INFO, String.format("%s enters as %s user.", emp.getPerson().getPersonName(), emp.getOccupation().getOccupation()));

                SceneHelper.switchTo(Scenes.USER);
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new NullPointerException(e.getMessage());
        }

    }
}
