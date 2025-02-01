package com.oopproject.wineryapplication.managment.orders;

import com.oopproject.wineryapplication.access.daos.BatchDao;
import com.oopproject.wineryapplication.access.daos.ClientsOrderDao;
import com.oopproject.wineryapplication.access.entities.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    public Double volumeForOrder(){
        return totalNeededVolume - storedBatchesForOrder().stream().mapToInt(BatchStoridge::getVolumeStored).sum();
    }

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
////            currentSum = batchStoridge.getVolumeStored();
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