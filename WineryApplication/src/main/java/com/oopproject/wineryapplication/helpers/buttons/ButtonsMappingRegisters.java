package com.oopproject.wineryapplication.helpers.buttons;

public enum ButtonsMappingRegisters {

    STORAGEORGANISER(0b1111),
    CEO             (0b1111),
    ACCOUNTANT      (0b1111),
    DEVISIONLEAD    (0b1111),

    MANAGEWORKERS      (0b00001100000100000000000),
    MANAGEWINEINVENTORY(0b11100010010010000001100),
    MANAGEORDERS       (0b00010000000001100000000),
    MANAGEMACHINES     (0b00000001101000000000000);

    private Integer buttonsMapping;

    ButtonsMappingRegisters(Integer buttonsMapping) {
        this.buttonsMapping = buttonsMapping;
    }

    public Integer getButtonsMapping() {
        return buttonsMapping;
    }
}
