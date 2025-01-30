package com.oopproject.wineryapplication.managment.production.bottling;

import com.oopproject.wineryapplication.access.entities.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BottleBatch {
    Bottle bottledBatch;
    Batch batch;
    Integer bottlesToFill;

    public BottleBatch(Batch batch, Sweetness sweetness, Short residualSugar, Integer bottlesToFill, BottleType bottleType) {
        this.batch = batch;
        this.bottlesToFill = bottlesToFill;
        bottledBatch = new Bottle();
        bottledBatch.setBatch(batch);
        bottledBatch.setResidualSugar(residualSugar);
        bottledBatch.setFilled(bottlesToFill);
        bottledBatch.setBottleType(bottleType);
    }

    public boolean bottle(){
        var ref = new Object() {
            Integer totalVolume = 0;
        };
        Set<BatchStoridge> batchStoridges = batch.getBatchStoridges();
        List<BatchStoridge> sortedStoridges = batchStoridges.stream()
                .sorted(Comparator.comparingInt(BatchStoridge::getVolumeStored))
                .toList();
        sortedStoridges.forEach(
                bs -> {
                    ref.totalVolume += bs.getVolumeStored();
                    bs.setVolumeStored(0);
                    bs.getDao().insert(bs);
                }
        );
        return true;
    }
}