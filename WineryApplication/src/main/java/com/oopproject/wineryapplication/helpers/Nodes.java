package com.oopproject.wineryapplication.helpers;

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
