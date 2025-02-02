package com.oopproject.wineryapplication.access.entities.entity.contracts;

import com.oopproject.wineryapplication.access.daos.dao.Dao;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;

/**
 * Представлява entity с уникален идентификатор и свързан обект за достъп до данни.
 *
 * Този интерфейс дефинира договор за класове, представящи entity-та,
 * които могат да бъдат съхранявани, извличани и актуализирани в източник на данни.
 *
 * Методи в този интерфейс:
 * - Осигуряват достъп до уникален идентификатор (`id`), свързан с entity-то.
 * - Позволяват извличане на Data Access Object (DAO), свързан с типа на entity-то.
 */
public interface Entitiable {
    /**
     * Извлича уникалния идентификатор, свързан с entity-то.
     *
     * @return уникалният идентификатор на entity-то като {@code Integer} или {@code null}, ако идентификаторът не е зададен.
     */
    Integer getId();

    /**
     * Задава уникалния идентификатор за entity-то.
     *
     * @param id уникалният идентификатор, който да бъде присвоен на entity-то, представен като {@code Integer}.
     *           Тази стойност може да бъде {@code null}, което показва, че entity-то може да няма присвоен ID все още.
     */
    void setId(Integer id);

    /**
     * Извлича Data Access Object (DAO), свързан с типа на entity-то.
     *
     * @return инстанцията на DAO, която предоставя CRUD операции и управление на данни за entity-то.
     */
    Dao getDao();
}