package com.oopproject.wineryapplication.helpers;

public enum Scenes {
    WELLCOME("/com/oopproject/wineryapplication/Wellcome.fxml"),
    LOG("/com/oopproject/wineryapplication/Log.fxml"),
    USER("/com/oopproject/wineryapplication/User.fxml"),
    ADDBASE("/com/oopproject/wineryapplication/AddBase.fxml"),
    DISPLAYBASE("/com/oopproject/wineryapplication/DisplayBase.fxml");

    private String fileName;

    Scenes(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
