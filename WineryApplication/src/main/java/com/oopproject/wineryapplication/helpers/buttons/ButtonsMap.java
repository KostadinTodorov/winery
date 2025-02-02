package com.oopproject.wineryapplication.helpers.buttons;

import java.util.Map;

/**
 * Interface representing a map of button actions, where each button is identified
 * by an integer key and associated with a specific action. The actions are encapsulated
 * using {@link ButtonsHelper.ButtonAction}, which provides the behavior to be executed
 * on interacting with the button.
 *
 * Implementations of this interface are expected to provide a mapping of button keys
 * to their corresponding actions. This allows for flexible button behavior assignment
 * and convenient retrieval of button actions at runtime.
 *
 * The interface defines the contract for accessing the button-action map, but it does not
 * enforce any specific way of initializing or organizing the map. Implementations may
 * define their own logic for populating and managing the map.
 */
public interface ButtonsMap {
    /**
     * Retrieves a mapping of integer keys to button actions.
     * Each entry in the map associates an integer identifier with a specific
     * {@link ButtonsHelper.ButtonAction} that defines the behavior of the corresponding button.
     *
     * @return a map where the keys are integers representing button identifiers
     *         and the values are {@link ButtonsHelper.ButtonAction} instances describing
     *         the actions to be executed for each button.
     */
    Map<Integer, ButtonsHelper.ButtonAction> getActionMap();
}