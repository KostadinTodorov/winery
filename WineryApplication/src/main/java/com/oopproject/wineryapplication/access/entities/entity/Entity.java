package com.oopproject.wineryapplication.access.entities.entity;

import com.oopproject.wineryapplication.access.entities.entity.contracts.Entitiable;
import com.oopproject.wineryapplication.access.entities.entity.contracts.EntityFieldNodeble;
import com.oopproject.wineryapplication.access.entities.entity.contracts.EntityPrintable;
//import com.oopproject.wineryapplication.access.entities.helper.EntityFieldMap;
import com.oopproject.wineryapplication.access.entities.mappers.EntityFieldMapper;
import com.oopproject.wineryapplication.access.entities.mappers.EntityTypeNodeMapper;
import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Abstract base class representing an entity in the system.
 * This class is designed to be extended by specific entity classes
 * and provides common functionality for all entities.
 *
 * This class implements the following interfaces:
 * - {@code Entitiable}: Indicates the entity is manageable in the context of the system.
 * - {@code EntityPrintable}: Provides support for generating a string representation.
 * - {@code EntityFieldNodeble}: Facilitates mapping of entity fields to nodes for data structure representation.
 *
 * Subclasses of this class typically represent specific entities in the application.
 * Examples include entities like "ClientsOrder", "Act", and "Employee".
 *
 * The intended purpose of this class and its associated subclasses is to encapsulate
 * data and behavior related to real-world entities, making them manageable and operable within
 * the application context.
 */
public abstract class Entity  implements Entitiable, EntityPrintable, EntityFieldNodeble{
    /**
     * Returns a string representation of the entity.
     * This representation includes the simple name of the class
     * and the unique identifier of the entity enclosed in square brackets.
     *
     * @return the string representation of the entity in the format: ClassName[ID]
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"["+getId()+"]";
    }

    /**
     * Converts the given {@code EntityFieldMapper} into a map that associates entity fields
     * with their corresponding nodes in the data structure.
     *
     * @param entityFieldMapper the mapper that provides the mapping between entity fields and nodes
     * @return a map where the keys are fields of the entity and the values are nodes in the associated data structure
     */
    @Override
    public Map<Field, Node> toFieldNodesMap(EntityFieldMapper entityFieldMapper) {
        if (entityFieldMapper == null) {
            throw new NullPointerException("EntityFieldMapper cannot be null");
        }
        return entityFieldMapper.getFieldNodeMap();
    }
}
