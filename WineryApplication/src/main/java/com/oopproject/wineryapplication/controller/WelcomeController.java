package com.oopproject.wineryapplication.controller;

import com.oopproject.wineryapplication.data.User;
import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;
import com.oopproject.wineryapplication.helpers.scenes.SceneHelper;
import com.oopproject.wineryapplication.helpers.scenes.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Клас {@code WelcomeController} отговаря за началната сцена на приложението,
 * предоставяйки възможност на потребителите да изберат тяхната роля
 * (CEO, Организатор на склада, Счетоводител или Лидер на отдел) и
 * преминаване към екрана за вход.
 *
 * Ключови функции:
 * - Записва информация за събития с помощта на {@link LoggerHelper}.
 * - Настройва ролята на потребителя чрез {@link User#setEmployeeOccupationBasedOnWelcome(String)}.
 * - Осъществява преход към екрана за вход с {@link SceneHelper#switchTo(Scenes)}.
 */
public class WelcomeController {

    @FXML
    Button btnCEO;
    @FXML
    Button btnStorageOrganiser;
    @FXML
    Button btnAccountant;
    @FXML
    Button btnDevisionLead;

    /**
     * Методът {@code initialize} се използва за инициализиране на контролера {@link WelcomeController}.
     * При извикване методът записва лог съобщение с ниво {@link LoggerLevels#INFO}, указвайки,
     * че инициализацията на контролера е започнала. Логването се извършва с помощта на
     * {@link LoggerHelper#logData(Class, LoggerLevels, String)}.
     *
     * @see LoggerHelper#logData(Class, LoggerLevels, String)
     */
    @FXML
    public void initialize() {
        LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "Initialize Welcome Controller");
    }

    /**
     * Методът {@code switchToLogin} обработва събитие от типа {@link ActionEvent}, стартирано от потребителски избор,
     * и извършва следните действия:
     * <ul>
     * <li>Регистрира лог съобщение за определената роля на потребителя с помощта на {@link LoggerHelper#logData(Class, LoggerLevels, String)}.</li>
     * <li>Настройва ролята на потребителя чрез {@link User#setEmployeeOccupationBasedOnWelcome(String)}
     *      на база избрания бутон от сцената (например CEO, Организатор на склада, Счетоводител или Лидер на отдел).</li>
     * <li>Превключва сцената към {@code LOG} екрана чрез {@link SceneHelper#switchTo(Scenes)}.</li>
     * </ul>
     *
     * @param event {@link ActionEvent}, който се задейства при натискане на един от бутоните за избор на роля.
     *              Чрез параметъра {@code event.getSource()} се идентифицира кой бутон е натиснат.
     * @throws IOException ако възникне грешка при промяната на сцената.
     */
    @FXML
    protected void switchToLogin(ActionEvent event) throws IOException {

        Object source = event.getSource();

        if(btnCEO == source){

            //System.out.println("CEO pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "CEO pressed");
            User.setEmployeeOccupationBasedOnWelcome("ceo");

        } else if (btnStorageOrganiser == source) {

            //System.out.println("Storage organiser pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "Storage organiser pressed");
            User.setEmployeeOccupationBasedOnWelcome("storage organiser");

        } else if (btnAccountant == source) {

            //System.out.println("Accountant pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "Accountant pressed");
            User.setEmployeeOccupationBasedOnWelcome("accountant");

        } else if (btnDevisionLead == source) {

            //System.out.println("Devision Lead pressed");
            LoggerHelper.logData(WelcomeController.class, LoggerLevels.INFO, "Devision Lead pressed");
            User.setEmployeeOccupationBasedOnWelcome("devision lead");

        }

        SceneHelper.switchTo(Scenes.LOG);
    }

}
