package com.oopproject.wineryapplication.helpers.scenes;

/**
 * Enum класът {@code Scenes} представлява набор от сцени (или екрани),
 * които могат да се използват в приложението за управление на различните потребителски изгледи.
 * Всеки елемент от енумерацията съдържа път към съответен {@code .fxml} файл.
 * Това позволява централизирано управление и достъп до различните екрани на потребителския интерфейс.
 */
public enum Scenes {
    WELLCOME("/com/oopproject/wineryapplication/Wellcome.fxml"),
    LOG("/com/oopproject/wineryapplication/Log.fxml"),
    USER("/com/oopproject/wineryapplication/User.fxml");

    private String fileName;

    Scenes(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
