package com.oopproject.wineryapplication.access.entities.entity.contracts;

//import com.oopproject.wineryapplication.access.entities.helper.EntityFieldMap;
import com.oopproject.wineryapplication.access.entities.mappers.EntityFieldMapper;
import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Представлява договор за преобразуване на полетата на entity-то в съпоставяне на полета към UI възли.
 *
 * Този интерфейс предоставя единствен метод, който съпоставя полетата на entity-то със съответните UI възли,
 * улеснявайки взаимодействието между моделите на данни и графичните потребителски интерфейси.
 */
public interface EntityFieldNodeble {
    /**
     * Преобразува полетата на entity-то в съпоставяне на полета към съответните UI възли.
     *
     * @param entityFieldMapper инстанция на {@code EntityFieldMapper}, която предоставя съпоставянето на полета към възли.
     * @return {@code Map}, където ключовете са обекти {@code Field}, представляващи полетата на entity-то,
     *         а стойностите са обекти {@code Node}, представляващи съответните UI компоненти.
     */
    Map<Field,Node> toFieldNodesMap(EntityFieldMapper entityFieldMapper);
//    Entity fromNode(Map<Field,Node> fieldNodeMap);
}