package com.oopproject.wineryapplication.access.entities.mappers;

import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Map;

public interface EntityFieldMapper {
    public Map<Field, Node> getFieldNodeMap();
}
