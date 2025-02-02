package com.oopproject.wineryapplication.helpers.scenes;

import com.oopproject.wineryapplication.helpers.buttons.ButtonsNotificationsRegisters;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Класът {@code SceneHelper} предоставя помощни методи за управление на сцени в JavaFX приложения.
 * Той включва методи за задаване на текуща сцена, превключване между различни сцени,
 * добавяне на възли към сцена и управление на изскачащи (pop-up) прозорци.
 */
public class SceneHelper
{

    private static Scene scene;

    public static void setScene(Scene scene)
    {
        SceneHelper.scene = scene;
    }

    /**
     * Превключва текущата сцена на JavaFX приложението към зададена сцена.
     * Методът зарежда нов FXML файл, свързан с дадения {@link Scenes}, и задава кореновия
     * възел на текущата сцена.
     *
     * @param selectedScene Енумерация от тип {@link Scenes}, която указва новата сцена, към която трябва да се превключи.
     *                      Използва се методът {@link Scenes#getFileName()} за извличане на пътя към съответния
     *                      {@code .fxml} файл.
     * @throws RuntimeException ако възникне грешка при зареждането на FXML файла (напр. {@link IOException}).
     */
    public static void switchTo(Scenes selectedScene) {
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



    /**
     * Превключва текущата сцена на JavaFX приложението към зададена сцена и
     * ѝ задава конкретен {@code controller}.
     * Методът зарежда нов FXML файл, свързан с предоставения {@link Scenes},
     * и задава предоставения обект като негов контролер.
     *
     * @param <T> Типът на обекта, използван като контролер.
     * @param selectedScene Инстанция на {@link Scenes}, представляваща избраната сцена за зареждане.
     *                      Сцената се идентифицира чрез нейния {@code .fxml} файл, извлечен чрез
     *                      {@link Scenes#getFileName()}.
     * @param controller Обект от тип {@code T}, който ще бъде зададен като контролер на новата сцена.
     *                   Контролерът управлява взаимодействието между логиката на приложението и изгледа.
     * @throws RuntimeException Ако възникне грешка при зареждането на {@code FXML} файла.
     */
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


    /**
     * Методът {@code addNode} добавя нов {@link AnchorPane} възел към даден {@link Pane},
     * използвайки предоставения {@link Nodes} за зареждане на съответния FXML файл.
     * Той задава и контролер, който да управлява новия възел.
     *
     * @param <T> Типът на контролера, който ще бъде зададен за новия възел.
     * @param paneNode Обект от тип {@link Pane}, където новият възел ще бъде визуализиран.
     *                 Съществуващите деца на този възел ще бъдат премахнати и заменени с новия възел.
     * @param node {@link Nodes} елемент, който указва кой FXML файл да се зареди за създаване на новия възел.
     *             Пътят към файла се извлича чрез метода {@link Nodes#getFileName()}.
     * @param controller Контролер от тип {@code T}, който ще се използва за управление на заредения FXML възел.
     *
     * @throws RuntimeException в случай на грешка при зареждането на FXML файла,
     *                          като например {@link IOException}.
     *
     * @see FXMLLoader#setController(Object)
     * @see AnchorPane
     */
    public static <T> void addNode(Pane paneNode, Nodes node, T controller) {
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

    /**
     * Задава и показва нов изскачащ прозорец (popup) в JavaFX приложението, базиран на зададен FXML файл,
     * заглавие и контролер. Методът зарежда посочения {@link ButtonsNotificationsRegisters}
     * {@code .fxml} файл като изглед, използвайки подадения {@code controller} за управляващ логиката
     * и задава заглавие на прозореца.
     *
     * @param <T> Типът на контролера, който ще бъде използван в новия прозорец.
     * @param notificationFXML Енумерация от тип {@link ButtonsNotificationsRegisters}, която определя
     *                         пътя до FXML файла за съдържанието на прозореца. Пътят е извлечен чрез
     *                         метода {@link ButtonsNotificationsRegisters#getFileName()}.
     * @param title            Стринг, който задава заглавието на прозореца.
     * @param controller       Обект от тип {@code T}, който ще бъде зададен като контролер на новия прозорец.
     *                         Контролерът управлява взаимодействието между логиката на приложението и изгледа.
     * @throws RuntimeException Ако възникне грешка при зареждането на {@code FXML} файла, напр. {@link IOException}.
     */
    public static <T> void switchToPopUp(ButtonsNotificationsRegisters notificationFXML, String title, T controller) {
        try {
            Stage popUpStage = new Stage();
            FXMLLoader loader = new FXMLLoader(SceneHelper.class.getResource(notificationFXML.getFileName()));
            loader.setController(controller);
            Parent root = loader.load(); // Load FXML file
            Scene newScene = new Scene(root, 700, 400);
            popUpStage.setScene(newScene);
            popUpStage.setTitle(title);
            popUpStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}