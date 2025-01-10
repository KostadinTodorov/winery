package com.oopproject.wineryapplication.helper;

public enum Scenes {
    WELLCOME("Wellcome.fxml"),
    LOG("Log.fxml"),
    USER("User.fxml"),
    ADDBASE("AddBase.fxml");

    private String fileName;

    Scenes(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
