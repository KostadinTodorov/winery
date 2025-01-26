package com.oopproject.wineryapplication.access.entities.entity.contracts;

import com.oopproject.wineryapplication.access.entities.helper.EntityTypeNodeMapper;
import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Map;

public interface EntityFieldNodeble {
    Map<Field,Node> toNode(EntityTypeNodeMapper entityTypeNodeMapper);
//    Entity fromNode(Map<Field,Node> fieldNodeMap);
}
