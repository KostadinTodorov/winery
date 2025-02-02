package com.oopproject.wineryapplication.access.entities.entity.contracts;

//import com.oopproject.wineryapplication.access.entities.helper.EntityFieldMap;
import com.oopproject.wineryapplication.access.entities.mappers.EntityFieldMapper;
import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Represents a contract for converting an entity's fields into a mapping of fields to UI nodes.
 *
 * This interface provides a single method that maps fields of an entity to corresponding UI nodes,
 * facilitating interaction between data models and graphical user interfaces.
 */
public interface EntityFieldNodeble {
    /**
     * Converts the fields of an entity into a mapping of fields to corresponding UI nodes.
     *
     * @param entityFieldMapper an {@code EntityFieldMapper} instance that provides the mapping of fields to nodes.
     * @return a {@code Map} where the keys are {@code Field} objects representing the entity's fields,
     *         and the values are {@code Node} objects representing the corresponding UI components.
     */
    Map<Field,Node> toFieldNodesMap(EntityFieldMapper entityFieldMapper);
//    Entity fromNode(Map<Field,Node> fieldNodeMap);
}
