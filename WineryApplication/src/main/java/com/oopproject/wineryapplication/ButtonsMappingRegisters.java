package com.oopproject.wineryapplication;

public enum ButtonsMappingRegisters {

    ADMINISTRATOR(0b1000),
    STORAGEORGANISER(0b0010),
    CEO(0b1111),
    ACCOUNTANT(0b1111),
    CRUD(0b0111);

    private Integer buttonsMapping;

    ButtonsMappingRegisters(Integer buttonsMapping) {
        this.buttonsMapping = buttonsMapping;
    }

    public Integer getButtonsMapping() {
        return buttonsMapping;
    }
}
