package com.oopproject.wineryapplication.access.entities.entity.contracts;

/**
 * Дефинира договор за entity-та, които могат да бъдат представени като низ.
 *
 * Този интерфейс изисква имплементацията на метода {@code toString},
 * позволявайки на обектите да предоставят смислено string представяне на състоянието си.
 */
public interface EntityPrintable {
    /**
     * Връща string представяне на обекта.
     *
     * String представянето обикновено включва името на класа на обекта,
     * неговия уникален идентификатор и, по желание, допълнителни подходящи атрибути.
     *
     * @return string представяне на обекта
     */
    @Override
    String toString();
}