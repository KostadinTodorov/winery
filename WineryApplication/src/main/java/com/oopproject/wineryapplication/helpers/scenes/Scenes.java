package com.oopproject.wineryapplication.helpers.scenes;

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
