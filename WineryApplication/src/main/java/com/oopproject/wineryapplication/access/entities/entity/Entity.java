package com.oopproject.wineryapplication.access.entities.entity;

import com.oopproject.wineryapplication.access.entities.entity.contracts.Entitiable;
import com.oopproject.wineryapplication.access.entities.entity.contracts.EntityFieldNodeble;
import com.oopproject.wineryapplication.access.entities.entity.contracts.EntityPrintable;
import com.oopproject.wineryapplication.access.entities.helper.EntityTypeNodeMapper;
import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class Entity  implements Entitiable, EntityPrintable, EntityFieldNodeble{
    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"["+getId()+"]";
    }

    @Override
    public Map<Field, Node> toNode(EntityTypeNodeMapper entityTypeNodeMapper) {
        return entityTypeNodeMapper.getFieldNodeMap();
    }
}
