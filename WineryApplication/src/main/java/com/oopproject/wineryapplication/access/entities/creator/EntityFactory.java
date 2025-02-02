package com.oopproject.wineryapplication.access.entities.creator;

import com.oopproject.wineryapplication.access.entities.entity.Entity;

/**
 * Дефинира интерфейс на фабрика за създаване на инстанции на класа {@link Entity}.
 * Имплементациите на този интерфейс са отговорни за капсулиране на логиката, необходима
 * за създаване и инициализиране на обект, потенциално използвайки специфични映射
 * или друга контекстна информация.
 */
public interface EntityFactory {
    /**
     * Създава и връща нова инстанция на класа Entity. Методът е отговорен
     * за инициализиране и попълване на полетата на обекта със съответните
     * стойности, което може да включва картографиране на входни данни от потребителя или друга контекстна информация.
     *
     * @return новосъздаден и инициализиран екземпляр на класа Entity
     */
    public Entity createEntity();
}