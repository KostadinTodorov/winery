package com.oopproject.wineryapplication.helpers.buttons;

import com.oopproject.wineryapplication.access.entities.entity.Entity;

/**
 * {@code EntityProvider} е функционален интерфейс, който представлява стратегия за доставяне
 * на обекти от тип {@link Entity}. Този интерфейс може да бъде имплементиран от класове
 * или използван директно чрез ламбда изрази и референтни методи.
 *
 * Чрез използване на {@code EntityProvider}, разработчиците могат да дефинират
 * динамично как се създават или извличат обектите {@link Entity}, подобрявайки модулността
 * и повторната използваемост на кода.
 */
// Ensures that there is only one abstract method. This allows it to be used in lambda expressions.
@FunctionalInterface
public interface EntityProvider {
    /**
     * Предоставя обект от тип {@link Entity}, използвайки имплементацията на
     * функционалния интерфейс {@code EntityProvider}.
     *
     * Методът {@code provide} представлява основната операция в интерфейса {@code EntityProvider}.
     * Той се използва за предоставяне на нова или съществуваща инстанция на {@link Entity},
     * като начинът на създаване или извличане на обекта зависи от конкретната имплементация.
     *
     * Тази функционалност е полезна за отделяне на логиката за създаване на обектите
     * от тяхното използване и позволява лесна подмяна на доставчика при нужда.
     *
     * @return обект от тип {@link Entity}, предоставен от имплементацията на метода
     */
    Entity provide();
}
