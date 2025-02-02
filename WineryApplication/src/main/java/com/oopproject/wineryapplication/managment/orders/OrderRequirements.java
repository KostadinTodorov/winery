package com.oopproject.wineryapplication.managment.orders;

import com.oopproject.wineryapplication.access.daos.BatchDao;
import com.oopproject.wineryapplication.access.daos.ClientsOrderDao;
import com.oopproject.wineryapplication.access.entities.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Клас, който моделира изискванията за поръчка, международни обеми и връзките с наличните партиди и съхранени партиди.
 * Осигурява методи за изчисление на нужния обем, наличностите и необходимата реколта за изпълнение на поръчката.
 */
public class OrderRequirements {
    private final ClientsOrder order;
    private List<BatchStoridge> storedBatchesForOrder;
    private List<Batch> batchesForOrder;
    private double totalNeededVolume;
    public OrderRequirements(ClientsOrder order) {
        if (order.getId() != null) {
            this.order = order;
            this.totalNeededVolume = order.getQuantity() * 0.75;
        }
        throw new IllegalArgumentException("Order ID cannot be null");
    }

    public double getTotalNeededVolume() {
        return totalNeededVolume;
    }

    public ClientsOrder getOrder() {
        return new ClientsOrderDao().get(order.getId());
    }

    /**
     * Изчислява оставащия обем, необходим за изпълнение на поръчката.
     * Методът изчислява разликата между общо необходимия обем за поръчката
     * и сумата от обемите, съхранени в {@link BatchStoridge}, налични за поръчката.
     *
     * @return {@code Double} представяща оставащия обем, който трябва да се добави,
     * за да се изпълни поръчката. Ако всички необходими обеми вече са съхранени,
     * резултатът ще бъде 0. Ако не достигат, ще се върне положителна стойност.
     */
    public Double volumeForOrder(){
        return totalNeededVolume - storedBatchesForOrder().stream().mapToInt(BatchStoridge::getVolumeStored).sum();
    }

    /**
     * Връща списък от обекти от тип {@link Batch}, които са налични и подходящи за изпълнение на поръчката.
     * Методът филтрира всички налични партиди, така че върнатите партиди да отговарят на типа вино на
     * текущата поръчка и да имат наличен обем, съхраняван в съответните {@link BatchStoridge}.
     * Резултатът се изчислява само веднъж и се кешира, за да се избегне повторно извеждане в бъдеще.
     *
     * @return Списък от обекти {@link Batch}, които съответстват на типа вино на поръчката и имат
     * наличен обем за покриване на изискванията.
     */
    public List<Batch> batchesForOrder(){
        if (batchesForOrder == null) {
            batchesForOrder = new BatchDao().getAll().stream().filter(
                    b ->
                            b.getWineType().equals(order.getWineType()) &&
                                    b.getBatchStoridges().stream().mapToInt(BatchStoridge::getVolumeStored).sum() > 0
            ).toList();
        }
        return batchesForOrder;
    }

    /**
     * Методът връща списък от обекти {@link BatchStoridge}, съхранени за дадена поръчка.
     * В случай че кеширането {@code storedBatchesForOrder} е празно (null), методът
     * извлича наличните {@link BatchStoridge} обекти от метод {@link #batchesForOrder()},
     * след което прави плоска мапинг операция с поток към всички съхранени {@link BatchStoridge}.
     * Резултатният списък след това се кешира и връща.
     *
     * @return Списък от обекти {@link BatchStoridge}, съдържащ всички съхранени партиди за дадена поръчка.
     */
    public List<BatchStoridge> storedBatchesForOrder(){
        if (storedBatchesForOrder == null) {
            List<BatchStoridge> usableBatchesContainers = batchesForOrder().stream().flatMap(
                    batch ->
                            batch.getBatchStoridges().stream()
            ).toList();
//            storedBatchesForOrder = bestCombination(usableBatchesContainers);
            return storedBatchesForOrder = usableBatchesContainers;
        }
        return storedBatchesForOrder;
    }

//    private List<BatchStoridge> bestCombination(List<BatchStoridge> batchStoridges){
//        Integer wantedVolume = order.getQuantity();
//        List<BatchStoridge> bestCombination = new ArrayList<>();
//        BatchStoridge bestFinalBatchStoridge = null;
//        int closestDifference = Integer.MAX_VALUE;
//        batchStoridges.sort(Comparator.comparingInt(BatchStoridge::getVolumeStored));
//        for (int i = 0; i < batchStoridges.size(); i++) {
//            if (closestDifference != 0) {
//                BatchStoridge ibatchStoridge = batchStoridges.get(i);
//                int currentDiff = wantedVolume - ibatchStoridge.getVolumeStored();
//                if (currentDiff != 0) {
//                    List<BatchStoridge> combination = new ArrayList<>();
//                    for (int j = i; j < batchStoridges.size(); j++) {
//                        BatchStoridge jbatchStoridge = batchStoridges.get(j);
//                        currentDiff -= jbatchStoridge.getVolumeStored();
//                        if(currentDiff == 0) {
//                            closestDifference = currentDiff;
//                            combination.add(jbatchStoridge);
//                            bestCombination = combination;
//                            break;
//                        }
//                        if (currentDiff > 0){
//                            combination.add(jbatchStoridge);
//                        } else {
//                            if (currentDiff < closestDifference){
//                                closestDifference = currentDiff;
//                                combination.add(jbatchStoridge);
//                                bestCombination = combination;
//                            } else {
//
//                            }
//                        }
//                    }
//                }
//            } else {
//                break;
//            }
//        }
//
//        return bestCombination;
//    }
//
//    public static boolean isBitSet(int number, int position) {
//        int divisor = (int) Math.pow(2, position);
//        return (number / divisor) % 2 == 1;
//    }

    /**
     * Изчислява списък с необходимите реколти {@link Harvest} за изпълнение на поръчка,
     * базирайки се на предоставените съотношения на сортовете {@link Sort} и процент на загуба.
     *
     * Методът използва наличните съотношения между различните сортове грозде и
     * общия необходим обем за поръчката {@link #volumeForOrder()}, за да изчисли какво количество
     * грозде (като тегло) е нужно от всяка реколта. Включва и корекция за процент на загуба,
     * който влияе върху обема, необходим за отделен сорт.
     *
     * @param sortRatioMap Карта, съдържаща съотношенията на сортовете {@link Sort} към общия обем.
     *                     Ключовете са сортове {@link Sort}, а стойностите са техните съотношения като {@code Float}.
     * @param lossPercentage Процент на загуба върху общия обем, предоставен като цяло число ({@code int}).
     *                       Например, 10 означава 10% загуба.
     * @return Списък с обекти {@link Harvest}, съдържащи необходимите тегла (включващи корекция за загуби)
     *         за всеки сорт грозде на базата на общия обем за поръчката.
     */
    public List<Harvest> harvestsNeededForOrder(Map<Sort, Float> sortRatioMap, int lossPercentage) {
        List<Harvest> harvestsNeeded = new ArrayList<>();
        double neededVolume = volumeForOrder();

        if (neededVolume > 0) {
            float whole = sumMapValues(sortRatioMap);
            for (Map.Entry<Sort, Float> entry : sortRatioMap.entrySet()) {
                Sort sort = entry.getKey();
                float ratio = entry.getValue();
                double sortVolume = neededVolume * (ratio / whole) * 100;

                // Assuming you have a method to convert volume to weight
                // (e.g., based on average yield per grape sort)
                int sortWeight = (int) (sortVolume + sortVolume-sortVolume*lossPercentage);

                Harvest harvest = new Harvest();
                harvest.setSort(sort);
                harvest.setWeight(sortWeight);
                harvestsNeeded.add(harvest);
            }
        }
        return harvestsNeeded;
    }

    public static float sumMapValues(Map<Sort, Float> ratioMap) {
        return ratioMap.values().stream()
                .reduce(0f, Float::sum);
    }

}