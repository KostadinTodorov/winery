package com.oopproject.wineryapplication.helpers.buttons;

import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;

import java.util.Map;

public class ButtonsMapHolderForEachCategory extends ButtonsMapBase{

    @Override
    protected Map<Integer, ButtonsHelper.ButtonAction> initializeMap() {
        LoggerHelper.logData(ButtonsMapHolderForEachCategory.class, LoggerLevels.INFO, "Creation of the category buttons");

        return Map.ofEntries(
                Map.entry(0, new ButtonsHelper.CategoryButtonAction("Manage workers")),
                Map.entry(1, new ButtonsHelper.CategoryButtonAction("Manage wine inventory")),
                Map.entry(2, new ButtonsHelper.CategoryButtonAction("Manage orders")),
                Map.entry(3, new ButtonsHelper.CategoryButtonAction("Manage machines"))
        );
    }
}
