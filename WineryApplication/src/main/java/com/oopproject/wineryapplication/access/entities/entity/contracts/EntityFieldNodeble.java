package com.oopproject.wineryapplication.access.entities.entity.contracts;

//import com.oopproject.wineryapplication.access.entities.helper.EntityFieldMap;
import com.oopproject.wineryapplication.access.entities.mappers.EntityFieldMapper;
import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Map;

public interface EntityFieldNodeble {
    Map<Field,Node> toFieldNodesMap(EntityFieldMapper entityFieldMapper);
//    Entity fromNode(Map<Field,Node> fieldNodeMap);
}
