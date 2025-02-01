package com.oopproject.wineryapplication.managment.production.mixing;

import com.oopproject.wineryapplication.access.daos.BatchDao;
import com.oopproject.wineryapplication.access.daos.BatchStoridgeDao;
import com.oopproject.wineryapplication.access.daos.HarvestDao;
import com.oopproject.wineryapplication.access.daos.MixDao;
import com.oopproject.wineryapplication.access.entities.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class MixBatch {
    private List<Harvest> harvests;
    private Set<Mix> mixSet;
    private Batch batch;
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