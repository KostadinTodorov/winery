package com.oopproject.wineryapplication;

public enum Scenes {
    WELLCOME("Wellcome.fxml"),
    LOG("Log.fxml"),
    USER("User.fxml");

    private String fileName;

    Scenes(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
