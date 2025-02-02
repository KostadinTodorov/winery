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
 * Класът {@code MixBatch} управлява взаимодействието между реколти ({@link Harvest}), контейнери
 * ({@link Container}) и партиди ({@link Batch}), като организира създаването и разпределението на партида
 * в предоставените контейнери. Също така осъществява връзката между реколтите и партидата чрез смеси ({@link Mix}).
 *
 * <p> Отговорности:
 * <ul>
 *     <li>Инициализира партида от даден обем и тип вино.</li>
 *     <li>Асоциира реколти с партидата чрез {@link Mix} обекти.</li>
 *     <li>Оптимално разпределя обема на партидата в наличните контейнери.</li>
 * </ul>
 *
 * <p> Класът използва DAO обекти, за да създава и съхранява различните данни в базата данни.</p>
 */
public class MixBatch {
    private List<Harvest> harvests;
    private Set<Mix> mixSet;
    private Batch batch;
    /**
     * Конструкторът {@code MixBatch} създава нова партида вино, задава нейния тип и обем,
     * свързва я с предоставените реколти и разпределя количеството из в предоставените контейнери.
     *
     * Този метод използва класове като:
     * <ul>
     *     <li>{@link WineType} - за задаване на типа на виното.</li>
     *     <li>{@link Harvest} - за връзка между партида и реколти.</li>
     *     <li>{@link Container} - за съхранение на количеството от партидата в контейнери.</li>
     *     <li>{@link Batch} - за създаване на нова партида.</li>
     *     <li>{@link Mix} - за упоменаване на връзки между партидите и реколтите.</li>
     *     <li>{@link BatchStoridge} - за менажиране на обема в контейнерите.</li>
     * </ul>
     *
     * @param volume      Обемът на партидата, който трябва да бъде произведен и съхранен.
     * @param wineType    Типът на виното за създаваната партида. {@link WineType}.
     * @param containers  Списък с контейнери {@link Container}, в които ще се съхранява партидата.
     *                    Контейнерите се сортират низходящо по техния капацитет.
     * @param harvests    Списък с реколти {@link Harvest}, които ще се използват за производството на партидата.
     *
     * @return Новосъздаден обект {@link Batch}, който представлява партидата в системата.
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