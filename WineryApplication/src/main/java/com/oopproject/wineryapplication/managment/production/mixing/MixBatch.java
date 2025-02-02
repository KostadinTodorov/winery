package com.oopproject.wineryapplication.managment.production.mixing;

import com.oopproject.wineryapplication.access.daos.BatchDao;
import com.oopproject.wineryapplication.access.daos.BatchStoridgeDao;
import com.oopproject.wineryapplication.access.daos.HarvestDao;
import com.oopproject.wineryapplication.access.daos.MixDao;
import com.oopproject.wineryapplication.access.entities.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * The MixBatch class is responsible for managing the creation
 * and storage of batch and mix entities. It orchestrates the
 * association between harvests, containers, and a batch while
 * ensuring proper volume distribution into available containers.
 */
public class MixBatch {
    private List<Harvest> harvests;
    private Set<Mix> mixSet;
    private Batch batch;
    /**
     * Constructs a MixBatch object and manages the creation and storage of a Batch object
     * while associating it with Harvest and Container objects. This method handles the
     * distribution of the specified batch volume into the provided containers, ensuring
     * proper allocation based on container space.
     *
     * @param volume The total volume of the batch to be created.
     * @param wineType The type of wine associated with the batch.
     * @param containers A list of Container objects where the batch will be stored. The containers
     *                   are sorted to optimize space usage.
     * @param harvests A list of Harvest objects to be associated with the batch as part of Mix objects.
     */
    public MixBatch(Integer volume, WineType wineType, List<Container> containers, List<Harvest> harvests) {
        batch.setVolume(volume);
        batch.setWineType(wineType);
        batch = new BatchDao().insert(batch);
        MixDao mixDao = new MixDao();
        harvests.forEach(
                harvest -> {
                        Mix newMix = new Mix();
                        newMix.setHarvest(harvest);
                        newMix.setBatch(batch);
                        mixDao.add(newMix);
                }
        );
        containers.sort(Comparator.comparingInt(Container::getSpace));
        containers = containers.reversed();
        int volumeLeft = volume;
        BatchStoridgeDao batchStoridgeDao = new BatchStoridgeDao();
        for (Container container : containers) {
            if (volumeLeft > 0) {
                BatchStoridge batchStoridge = new BatchStoridge();
                int containerSpace = container.getSpace();

                if (volumeLeft >= containerSpace) {
                    batchStoridge.setVolumeStored(containerSpace);
                    volumeLeft -= containerSpace;
                } else {
                    batchStoridge.setVolumeStored(volumeLeft);
                }
                batchStoridge.setContainer(container);
                batchStoridge.setBatch(batch);
                batchStoridgeDao.add(batchStoridge);
            }
        }
    }

    public Batch getBatch() {
        return new BatchDao().get(batch.getId());
    }
}