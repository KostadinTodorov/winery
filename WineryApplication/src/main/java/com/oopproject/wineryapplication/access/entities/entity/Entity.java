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
 * Абстрактен базов клас, представляващ entity в системата.
 * Този клас е предназначен да бъде разширен от специфични класове entity
 * и предоставя обща функционалност за всички entity-та.
 *
 * Този клас имплементира следните интерфейси:
 * - {@code Entitiable}: Показва, че entity-то може да се управлява в контекста на системата.
 * - {@code EntityPrintable}: Осигурява поддръжка за генериране на string представяне.
 * - {@code EntityFieldNodeble}: Улеснява съпоставянето на полета на entity-то към възли за представяне на структурата на данните.
 *
 * Подкласовете на този клас обикновено представляват специфични entity-та в приложението.
 * Примери включват entity-та като "ClientsOrder", "Act" и "Employee".
 *
 * Предназначението на този клас и свързаните с него подкласове е да капсулират
 * данни и поведение, свързани с реални entity-та, което ги прави управляеми и операционни в
 * контекста на приложението.
 */
public abstract class Entity  implements Entitiable, EntityPrintable, EntityFieldNodeble{
    /**
     * Връща string представяне на entity-то.
     * Това представяне включва простото име на класа
     * и уникалния идентификатор на entity-то, затворени в квадратни скоби.
     *
     * @return string представянето на entity-то във формат: ClassName[ID]
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"["+getId()+"]";
    }

    /**
     * Преобразува дадения {@code EntityFieldMapper} в карта, която свързва полетата на entity-то
     * със съответните им възли в структурата на данните.
     *
     * @param entityFieldMapper mapper-ът, който предоставя съпоставянето между полетата на entity-то и възлите
     * @return карта, където ключовете са полета на entity-то, а стойностите са възли в свързаната структура на данни
     */
    @Override
    public Map<Field, Node> toFieldNodesMap(EntityFieldMapper entityFieldMapper) {
        if (entityFieldMapper == null) {
            throw new NullPointerException("EntityFieldMapper cannot be null");
        }
        return entityFieldMapper.getFieldNodeMap();
    }
}
