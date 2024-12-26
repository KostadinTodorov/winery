package com.oopproject.wineryapplication.helper;

public enum Nodes {
    BOTTLEDWINEINVENTORY("BottledWineInventory.fxml");

    private String fileName;

    Nodes(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
