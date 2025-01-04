package com.oopproject.wineryapplication;

public enum ButtonType {
    HELLO("Hello"),
    GOODBYE("Goodbye"),
    WELCOME("Welcome"),
    EXIT("Exit");

    private final String label;

    ButtonType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
