package com.oopproject.wineryapplication.helpers;

import com.oopproject.wineryapplication.access.entities.*;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class ButtonsMapHolder {

    public static Map<Integer, ButtonsHelper.ButtonAction> actionMap;

    static {
        Map<Integer, ButtonsHelper.ButtonAction> unsortedMap = Map.ofEntries(
                Map.entry(0, new ButtonsHelper.ButtonAction("Act", Act::new)),
                Map.entry(1, new ButtonsHelper.ButtonAction("Answer", Answer::new)),
                Map.entry(2, new ButtonsHelper.ButtonAction("Batch", Batch::new)),
                Map.entry(3, new ButtonsHelper.ButtonAction("Batch Storage", BatchStoridge::new)),
                Map.entry(4, new ButtonsHelper.ButtonAction("Behavior", Behavior::new)),
                Map.entry(5, new ButtonsHelper.ButtonAction("Bottle", Bottle::new)),
                Map.entry(6, new ButtonsHelper.ButtonAction("Bottle Type", BottleType::new)),
                Map.entry(7, new ButtonsHelper.ButtonAction("Client", Client::new)),
                Map.entry(8, new ButtonsHelper.ButtonAction("Clients Order", ClientsOrder::new)),
                Map.entry(9, new ButtonsHelper.ButtonAction("Company", Company::new)),
                Map.entry(10, new ButtonsHelper.ButtonAction("Container", Container::new)),
                Map.entry(11, new ButtonsHelper.ButtonAction("Employee", Employee::new)),
                Map.entry(12, new ButtonsHelper.ButtonAction("Fault Code", FaultCode::new)),
                Map.entry(13, new ButtonsHelper.ButtonAction("Harvest", Harvest::new)),
                Map.entry(14, new ButtonsHelper.ButtonAction("Machine", Machine::new)),
                Map.entry(15, new ButtonsHelper.ButtonAction("Machine Type", MachineType::new)),
                Map.entry(16, new ButtonsHelper.ButtonAction("Mix", Mix::new)),
                Map.entry(17, new ButtonsHelper.ButtonAction("Occupation", Occupation::new)),
                Map.entry(18, new ButtonsHelper.ButtonAction("Person", Person::new)),
                Map.entry(19, new ButtonsHelper.ButtonAction("Progress", Progress::new)),
                Map.entry(20, new ButtonsHelper.ButtonAction("Sort", Sort::new)),
                Map.entry(21, new ButtonsHelper.ButtonAction("Sweetness", Sweetness::new)),
                Map.entry(22, new ButtonsHelper.ButtonAction("Wine Type", WineType::new))
        );

        // Sort entries by button name
        actionMap = unsortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(ButtonsHelper.ButtonAction::getLabel)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // Preserve sorted order
                ));
    }
}
