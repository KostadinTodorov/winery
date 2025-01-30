package com.oopproject.wineryapplication.managment.purchases;

import com.oopproject.wineryapplication.access.entities.Harvest;
import com.oopproject.wineryapplication.access.entities.Sort;

public class PurchaseHarvest implements Purchase<Harvest> {
    private final Harvest harvest;

    public PurchaseHarvest(Sort sort, Integer weight) {
        harvest = new Harvest();
        harvest.setWeight(weight);
        harvest.setSort(sort);
    }

    @Override
    public Harvest buy() {
        return harvest.getDao().insert(harvest);
    }
}