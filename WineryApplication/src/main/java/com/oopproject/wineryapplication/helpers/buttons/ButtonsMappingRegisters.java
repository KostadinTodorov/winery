package com.oopproject.wineryapplication.helpers.buttons;

public enum ButtonsMappingRegisters {

    // CAT from category
    CATSTORAGEORGANISER(0b1111),
    CATCEO             (0b1111),
    CATACCOUNTANT      (0b1111),
    CATDEVISIONLEAD    (0b1111),

    // NTF from notification
    NTFSTORAGEORGANISER(0b1111),
    NTFCEO             (0b1111),
    NTFACCOUNTANT      (0b1111),
    NTFDEVISIONLEAD    (0b1111),

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
