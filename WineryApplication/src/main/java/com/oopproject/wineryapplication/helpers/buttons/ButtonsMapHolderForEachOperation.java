package com.oopproject.wineryapplication.helpers.buttons;

import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;

import java.util.Map;

public class ButtonsMapHolderForEachOperation extends ButtonsMapBase{

    @Override
    protected Map<Integer, ButtonsHelper.ButtonAction> initializeMap() {
        LoggerHelper.logData(ButtonsMapHolderForEachOperation.class, LoggerLevels.INFO, "Creation of the notification buttons");
        return Map.ofEntries(
                Map.entry(0, new ButtonsHelper.OperationButtonAction("Orders")),
                Map.entry(1, new ButtonsHelper.OperationButtonAction("Production")),
                Map.entry(2, new ButtonsHelper.OperationButtonAction("Bottling")),
                Map.entry(3, new ButtonsHelper.OperationButtonAction("Purchases"))
        );
    }
}
