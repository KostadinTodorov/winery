package com.oopproject.wineryapplication.helpers.buttons;

import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;

import java.util.Map;

public class ButtonsMapHolderForEachNotification extends ButtonsMapBase{

    @Override
    protected Map<Integer, ButtonsHelper.ButtonAction> initializeMap() {
        LoggerHelper.logData(ButtonsMapHolderForEachNotification.class, LoggerLevels.INFO, "Creation of the notification buttons");
        return Map.ofEntries(
                Map.entry(0, new ButtonsHelper.NotificationButtonAction("Orders")),
                Map.entry(1, new ButtonsHelper.NotificationButtonAction("Production")),
                Map.entry(2, new ButtonsHelper.NotificationButtonAction("Bottling")),
                Map.entry(3, new ButtonsHelper.NotificationButtonAction("Purchases"))
        );
    }
}
