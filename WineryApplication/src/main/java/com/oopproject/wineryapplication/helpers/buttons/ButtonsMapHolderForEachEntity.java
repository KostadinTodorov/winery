package com.oopproject.wineryapplication.helpers.buttons;

import java.util.Map;
import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;

public class ButtonsMapHolderForEachEntity extends ButtonsMapBase {

    @Override
    protected Map<Integer, ButtonsHelper.ButtonAction> initializeMap() {
        LoggerHelper.logData(ButtonsMapHolderForEachEntity.class, LoggerLevels.INFO, "Creation of the entity buttons");

        return Map.ofEntries(
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
    }
}
