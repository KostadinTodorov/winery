package com.oopproject.wineryapplication.managment.production.bottling;

import com.oopproject.wineryapplication.access.entities.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a batch of bottles created from a specific batch of wine or other liquid.
 * This class handles the association between a batch and its corresponding bottles along with
 * the process of bottling the batch.
 *
 * Key responsibilities:
 * - Creates and initializes a Bottle object with specific properties such as batch, residual sugar,
 *   number of bottles to be filled, and bottle type.
 * - Manages the bottling process by calculating the total volume from storage units and updating their states.
 *
 * Fields:
 * - bottledBatch: A Bottle entity representing the bottled product derived from the batch.
 * - batch: A Batch entity representing the source batch for bottling.
 * - bottlesToFill: An integer specifying the number of bottles to be filled from the given batch.
 *
 * Constructors:
 * - BottleBatch(Batch batch, Sweetness sweetness, Short residualSugar, Integer bottlesToFill, BottleType bottleType):
 *   Initializes the BottleBatch with the associated batch, sweetness, residual sugar level, number of bottles, and bottle type.
 *
 * Methods:
 * - bottle(): Executes the bottling process by clearing the storage associated with the batch,
 *   computing the total volume used, and persisting updated information in the database.
 */
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

    /**
     * Executes the bottling process for the batch associated with this BottleBatch instance.
     *
     * In this process, all storage units (BatchStoridge) related to the batch are iterated over.
     * The total stored volume from these units is accumulated, and each unit's stored volume
     * is reset to zero. Each updated storage unit is then persisted into the database through
     * its associated Data Access Object (DAO).
     *
     * @return true if the bottling process completes successfully
     */
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