package com.oopproject.wineryapplication.managment.purchases;

import com.oopproject.wineryapplication.access.entities.Harvest;
import com.oopproject.wineryapplication.access.entities.Sort;

/**
 * Клас {@code PurchaseHarvest} представлява конкретна имплементация на интерфейса
 * {@link Purchase}, която обработва покупката на обекти от тип {@link Harvest}.
 * <p>
 * Основната функционалност на класа е създаването на нов {@link Harvest} обект,
 * задаване на неговите атрибути и съхраняването му в базата данни чрез DAO слой.
 * <p>
 * Конструкторът на класа приема параметри за сорт и тегло, които се използват
 * за конфигуриране на създавания обект от тип {@link Harvest}.
 * <p>
 * Методи:
 * <ul>
 *     <li>{@link #PurchaseHarvest(Sort, Integer)}: Инициализира нов {@link Harvest} обект с дадените
 *     параметри за сорт и тегло.</li>
 *     <li>{@link #buy()}: {@inheritDoc}</li>
 * </ul>
 */
public class PurchaseHarvest implements Purchase<Harvest> {
    private final Harvest harvest;

    public PurchaseHarvest(Sort sort, Integer weight) {
        harvest = new Harvest();
        harvest.setWeight(weight);
        harvest.setSort(sort);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Harvest buy() {
        return harvest.getDao().insert(harvest);
    }
}