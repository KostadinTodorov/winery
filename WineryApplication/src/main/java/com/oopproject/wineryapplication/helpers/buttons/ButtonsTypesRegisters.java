package com.oopproject.wineryapplication.helpers.buttons;

public enum ButtonsTypesRegisters {


    CAT ("category"),
    NTF ("notifications"),
    ENT ("entities");


    private String buttonsTypes;

    ButtonsTypesRegisters(String buttonsTypes) {
        this.buttonsTypes = buttonsTypes;
    }

    public String getButtonsTypesRegisters() {
        return buttonsTypes;
    }
}
