package com.oopproject.wineryapplication.helpers.buttons;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

/**
 * Provides a base implementation for managing a map of button identifiers to corresponding
 * actions. Each button is identified by an integer key, and its functionality is defined
 * by a {@link ButtonsHelper.ButtonAction}.
 *
 * This abstract class simplifies the process of creating and managing button-action mappings
 * by offering a structure for initialization and optional sorting of the action map.
 * Subclasses are required to implement the {@link #initializeMap()} method to define their
 * specific button-action mappings.
 *
 * The class also includes a protected `Map` to store the mappings and a public method
 * to retrieve the finalized map for use.
 */
public abstract class ButtonsMapBase implements ButtonsMap {

    protected Map<Integer, ButtonsHelper.ButtonAction> actionMap;

    protected ButtonsMapBase() {
        actionMap = initializeMap();
        //sortActionMap();
    }

    // Subclasses must provide their button map
    protected abstract Map<Integer, ButtonsHelper.ButtonAction> initializeMap();

    /**
     * Sorts the `actionMap` by the labels of its corresponding {@link ButtonsHelper.ButtonAction} values.
     *
     * This method updates the `actionMap` by sorting its entries based on the natural order of the
     * labels of the {@link ButtonsHelper.ButtonAction} objects. The resulting map retains the
     * key-value associations but organizes them in the order determined by the labels.
     *
     * The sorting operation produces a new {@link LinkedHashMap} to preserve the order of entries.
     * The sorting is applied using a {@link Stream} of entries and converting it back to a map.
     *
     * The method uses:
     * - {@link Map.Entry#comparingByValue} with a comparator that accesses the `label` property
     *   of each {@link ButtonsHelper.ButtonAction}.
     * - {@link Collectors#toMap} to collect sorted entries into a {@link LinkedHashMap}, ensuring
     *   that the original order of insertion is replaced with the sorted order.
     *
     * Note: This method assumes that the `label` property of {@link ButtonsHelper.ButtonAction} is
     * not null and implements {@link Comparable} to define its natural sorting order.
     *
     * Implementation details:
     * - If multiple entries have the same label, their relative order is unspecified.
     * - Resolves key collisions by retaining the first encountered entry.
     */
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

    /**
     * {@inheritDoc}
     */
    public Map<Integer, ButtonsHelper.ButtonAction> getActionMap() {
        return actionMap;
    }
}