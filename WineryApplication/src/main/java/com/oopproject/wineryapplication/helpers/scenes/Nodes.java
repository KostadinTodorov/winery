package com.oopproject.wineryapplication.helpers.scenes;

/**
 * Enum класът {@code Nodes} представлява набор от възможни възли (или изгледи),
 * чрез които могат да се зареждат и управляват различни компоненти на потребителския интерфейс.
 * Всеки елемент от енумерацията съдържа път към съответен {@code .fxml} файл.
 * Класът е полезен за закачане и демонстрация на отделни части от приложението.
 */
public enum Nodes {
    ADDBASE("/com/oopproject/wineryapplication/AddBase.fxml"),
    DISPLAYBASE("/com/oopproject/wineryapplication/DisplayBase.fxml"),
    EDITBASE("/com/oopproject/wineryapplication/EditBase.fxml");
    private String fileName;

    Nodes(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
