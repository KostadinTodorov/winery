package com.oopproject.wineryapplication.helpers;

import com.oopproject.wineryapplication.access.entities.ClientsOrder;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.entities.Harvest;
import com.oopproject.wineryapplication.access.entities.WineType;

import java.util.Map;

public class ButtonsMapHolder {
    public static Map<Integer, ButtonsHelper.ButtonAction> actionMap = Map.of(
            0, new ButtonsHelper.ButtonAction("Workers",  Employee::new),
            1, new ButtonsHelper.ButtonAction("Wine",     WineType::new),
            2, new ButtonsHelper.ButtonAction("Harvest",  Harvest ::new),
            3, new ButtonsHelper.ButtonAction("Orders",   ClientsOrder::new)
    );
}
