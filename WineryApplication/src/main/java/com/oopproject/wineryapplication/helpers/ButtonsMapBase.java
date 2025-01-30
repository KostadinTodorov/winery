package com.oopproject.wineryapplication.helpers;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public abstract class ButtonsMapBase implements ButtonsMap {

    protected Map<Integer, ButtonsHelper.ButtonAction> actionMap;

    protected ButtonsMapBase() {
        actionMap = initializeMap();
        sortActionMap();
    }

    // Subclasses must provide their button map
    protected abstract Map<Integer, ButtonsHelper.ButtonAction> initializeMap();

    private void sortActionMap() {
        actionMap = actionMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(ButtonsHelper.ButtonAction::getLabel)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public Map<Integer, ButtonsHelper.ButtonAction> getActionMap() {
        return actionMap;
    }
}