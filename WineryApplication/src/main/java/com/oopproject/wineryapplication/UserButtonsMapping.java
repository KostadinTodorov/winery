package com.oopproject.wineryapplication;

public enum UserButtonsMapping {

    ADMINISTRATOR(0b1111),
    STORAGEORGANISER(0b1111),
    CEO(0b1111),
    ACCOUNTANT(0b1111);

    private Integer userMapping;

    UserButtonsMapping(Integer userMapping) {
        this.userMapping = userMapping;
    }

    public Integer getUserMapping() {
        return userMapping;
    }
}
