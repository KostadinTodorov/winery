package com.oopproject.wineryapplication.helpers.buttons;

public enum ButtonsMappingRegisters {

    // CAT from category
    CATSTORAGEORGANISER(0b1000010),
    CATCEO             (0b0100101),
    CATACCOUNTANT      (0b0010100),
    CATDEVISIONLEAD    (0b1111111),

    // OP from operation
    OPSTORAGEORGANISER(0b1111),
    OPCEO             (0b0000),
    OPACCOUNTANT      (0b1000),
    OPDEVISIONLEAD    (0b1111),

    // categories
    MANAGEWORKERS      (0b00001100000100000010001),
    MANAGEWINEINVENTORY(0b11100000000000000000000),
    MANAGEORDERS       (0b00010000000000100000000),
    MANAGEMACHINES     (0b00000001101000000000010),
    MANAGEBATCHSTORAGE (0b00000010010010000001100),
    MANAGECLIENTS      (0b00001000000001010000000),
    MANAGEBOTTLES      (0b00000000000000001100000);

    private Integer buttonsMapping;

    ButtonsMappingRegisters(Integer buttonsMapping) {
        this.buttonsMapping = buttonsMapping;
    }

    public Integer getButtonsMapping() {
        return buttonsMapping;
    }
}
