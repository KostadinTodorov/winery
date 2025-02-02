package com.oopproject.wineryapplication.helpers.buttons;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

/**
 * Абстрактният клас {@code ButtonsMapBase} служи като основа за управление на карта,
 * която асоциира числови идентификатори на бутони с техните съответни действия.
 * Този клас прилага интерфейса {@link ButtonsMap} и осигурява стандартна функционалност
 * за управление и сортиране на тази карта.
 *
 * Класът изисква от разширяващите го класове да предоставят специфична реализация
 * за инициализация на картата чрез метода {@link #initializeMap()}.
 */
public abstract class ButtonsMapBase implements ButtonsMap {

    protected Map<Integer, ButtonsHelper.ButtonAction> actionMap;

    protected ButtonsMapBase() {
        actionMap = initializeMap();
        //sortActionMap();
    }

    // Subclasses must provide their button map
    protected abstract Map<Integer, ButtonsHelper.ButtonAction> initializeMap();

    /**
     * Сортира картата {@code actionMap}, като подрежда записите в нарастващ ред по стойността на етикетите
     * (labels) на действията {@code ButtonAction}.
     * <p>
     * Методът използва {@link Stream} за преобразуване и сортиране на записите в картата
     * и създава нова инстанция на {@link LinkedHashMap}, за да запази подредбата на елементите
     * според сортирането.
     * </p>
     *
     * @implNote Сортирането се изпълнява, като се извика методът {@link Comparator#comparing} с референция
     * до метода {@code getLabel} на класа {@link ButtonsHelper.ButtonAction}. Новата карта е подредена,
     * използвайки {@link Collectors#toMap}, като се гарантира, че ключовете и стойностите няма да бъдат дублирани.
     *
     * @throws NullPointerException ако някоя от стойностите в {@code actionMap} е {@code null}.
     * @throws IllegalStateException ако има дублирани записи след сортирането. Това е предотвратено чрез
     * предоставеното разрешаване на конфликти в {@link Collectors#toMap}.
     */
    private void sortActionMap() {
        actionMap = actionMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(ButtonsHelper.ButtonAction::getLabel)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer, ButtonsHelper.ButtonAction> getActionMap() {
        return actionMap;
    }
}