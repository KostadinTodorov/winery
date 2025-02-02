package com.oopproject.wineryapplication.access.entities.mappers;

import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Provides a mapping between the fields of an entity and corresponding UI nodes.
 *
 * This interface defines a contract for classes that are responsible for creating
 * and managing a relationship between entity fields and specific UI components,
 * enabling seamless interaction between data models and the user interface.
 *
 * Implementations of this interface can utilize reflection to inspect the fields
 * of an entity class, dynamically associate them with relevant UI components,
 * and return the mapping as a {@code Map<Field, Node>}.
 */
public interface EntityFieldMapper {
    /**
     * Generates a map that associates the fields of the current entity class with corresponding UI nodes.
     * The type of node generated depends on the field type:
     * - For {@code Boolean} fields, a {@code CheckBox} is created.
     * - For {@code LocalDate} fields, a {@code DatePicker} is created.
     * - For fields referencing other entities, a {@code ComboBox} containing the entities is created.
     * - For all other field types, a {@code TextField} is created, with the type name used as a prompt text.
     *
     * The method uses reflection to retrieve all declared fields of the entity class, determines the type
     * of each field, and dynamically creates and maps the appropriate UI node.
     *
     * @return a map where the keys are the fields of the entity class and the values are the corresponding UI nodes
     */
    public Map<Field, Node> getFieldNodeMap();
}
