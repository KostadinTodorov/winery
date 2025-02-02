package com.oopproject.wineryapplication.helpers.buttons;

import java.util.Map;

/**
 * Интерфейсът {@code ButtonsMap} предоставя метод за извличане на карта,
 * която свързва числови идентификатори на бутони с техните съответни действия.
 * Основната идея е да се дефинира начин за организиране на действията на бутоните
 * чрез карта, където ключовете са идентификаторите на бутоните, а стойностите
 * са инстанции на {@link ButtonsHelper.ButtonAction}, описващи поведението на бутоните.
 */
public interface ButtonsMap {
    /**
     * Извлича карта, която свързва числови идентификатори с действия на бутони.
     * Картата представлява асоциация между {@code Integer} идентификаторите и обекти
     * от тип {@link ButtonsHelper.ButtonAction}.
     *
     * @return {@link Map}, която съдържа съответствието между числови идентификатори и
     * обекти от тип {@link ButtonsHelper.ButtonAction}.
     */
    Map<Integer, ButtonsHelper.ButtonAction> getActionMap();
}