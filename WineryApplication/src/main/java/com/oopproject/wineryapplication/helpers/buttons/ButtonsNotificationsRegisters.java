package com.oopproject.wineryapplication.helpers.buttons;

/**
 * {@code ButtonsNotificationsRegisters} представлява изброен тип, който дефинира константи,
 * свързващи определени известия или действия за бутони с техните ресурсни файлове.
 * Основната му цел е да предостави лесен и структуриран начин за достъп до пътищата
 * на ресурсни файлове, които са свързани с различни действия или известия на бутони.
 */
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
