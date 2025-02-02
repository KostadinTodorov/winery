package com.oopproject.wineryapplication.managment.production.bottling;

import com.oopproject.wineryapplication.access.entities.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@code BottleBatch} представлява клас, използван за управление на процеса на бутилиране
 * на определена партида от напитки. Този клас съчетава информация за бъчвата, бутилките, както
 * и количеството бутилки, които трябва да бъдат напълнени.
 * <p>
 * Основната функционалност на {@code BottleBatch} е да обединява данни за производствена партида
 * и съдържание за бутилирането, като улеснява и изпълнява процеса на "източване" от склада (BatchStoridge)
 * и запис на промени в базата данни.
 *
 * Полета:
 * <ul>
 *   <li>{@code bottledBatch}: Връзка към {@link Bottle}, отразяваща информация за конкретната бутилирана партида.</li>
 *   <li>{@code batch}: Връзка към {@link Batch}, показваща настоящата производствена партида за бутилиране.</li>
 *   <li>{@code bottlesToFill}: Цяло число, указващо броя на бутилките, които трябва да се напълнят.</li>
 * </ul>
 *
 * Конструктор:
 * <p>
 * {@link BottleBatch#BottleBatch(Batch, Sweetness, Short, Integer, BottleType)} изгражда инстанцията
 * на класа, като задава основните параметри, необходими за процеса на бутилиране. Той автоматично
 * създава обект за бутилка и го настройва с подадените характеристики като бъчва, сладост, остатъчна захар,
 * количество за пълнене и тип бутилка.
 *
 * <p>
 * Методи:
 * <ul>
 *   <li>{@link BottleBatch#bottle()}: Изпълнява процеса на бутилиране, където обемите от склада
 *   се "източват" и промените се записват в базата данни.</li>
 * </ul>
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
     * Изпълнява процеса на "източване" на обемите течност в склада за съответната партида и актуализира записите
     * в базата данни. Методът първо сортира складовите обеми {@link BatchStoridge} по техния съхранен обем
     * чрез {@link Comparator#comparingInt}. След това обхожда всяка складова единица, като добавя текущия обем
     * към общия събран обем, нулира стойността на съхранения обем, и чрез {@link BatchStoridge#getDao() DAO}
     * актуализира данните в базата.
     *
     * @return {@code true} винаги при успешно изпълнение.
     * Обърнете внимание, че този метод предполага успешна работа с базата данни и не обработва грешки.
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