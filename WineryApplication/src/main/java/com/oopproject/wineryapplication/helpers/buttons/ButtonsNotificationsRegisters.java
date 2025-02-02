package com.oopproject.wineryapplication.helpers.buttons;

public enum ButtonsNotificationsRegisters {


    ORDERS ("/com/oopproject/wineryapplication/Operations.fxml");

    private String buttonsNotifications;

    ButtonsNotificationsRegisters(String buttonsNotifications) {
        this.buttonsNotifications = buttonsNotifications;
    }

    public String getFileName() {
        return buttonsNotifications;
    }
}
