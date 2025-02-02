package com.oopproject.wineryapplication.helpers.buttons;

import java.util.Map;
import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;

/**
 * Класът {@code ButtonsMapHolderForEachEntity} е разширение на абстрактния клас {@link ButtonsMapBase}
 * и предоставя специфична реализация за инициализация на карта, съответстваща на бутони,
 * които изпълняват действия върху различни типове ентитети.
 * <p>
 * Картата асоциира цялочислени идентификатори с действия чрез {@link ButtonsHelper.ButtonAction},
 * които се използват за управление на функционалността на UI бутони, свързани с различни типове обекти.
 * </p>
 *
 * <p>Методите в този клас работят с карта от тип {@code Map<Integer, ButtonsHelper.ButtonAction>},
 * която се инициализира чрез метода {@link #initializeMap()}.</p>
 */
public class ButtonsMapHolderForEachEntity extends ButtonsMapBase {

    /**
     * Инициализира карта от тип {@code Map<Integer, ButtonsHelper.ButtonAction>}, която съдържа
     * асоциации между уникални идентификатори и съответстващите действия на бутоните.
     * Картата е предварително дефинирана с конкретни двойки {@link Map.Entry}, всяка от които
     * съответства на специфичен бутон и неговото действие.
     *
     * По време на инициализацията се логва информация относно създаването чрез {@link LoggerHelper}.
     *
     * @return карта, съставена от двойки {@link Map.Entry}, където ключовете са от тип
     * {@code Integer}, а стойностите са {@link ButtonsHelper.ButtonAction}, инстанциирани за специфични действия.
     * {@inheritDoc}
     */
    @Override
    protected Map<Integer, ButtonsHelper.ButtonAction> initializeMap() {
        LoggerHelper.logData(ButtonsMapHolderForEachEntity.class, LoggerLevels.INFO, "Creation of the entity buttons");

        return Map.ofEntries(
                Map.entry(0, new ButtonsHelper.EntityButtonAction("Act", Act::new)),
                Map.entry(1, new ButtonsHelper.EntityButtonAction("Answer", Answer::new)),
                Map.entry(2, new ButtonsHelper.EntityButtonAction("Batch", Batch::new)),
                Map.entry(3, new ButtonsHelper.EntityButtonAction("Batch Storage", BatchStoridge::new)),
                Map.entry(4, new ButtonsHelper.EntityButtonAction("Behavior", Behavior::new)),
                Map.entry(5, new ButtonsHelper.EntityButtonAction("Bottle", Bottle::new)),
                Map.entry(6, new ButtonsHelper.EntityButtonAction("Bottle Type", BottleType::new)),
                Map.entry(7, new ButtonsHelper.EntityButtonAction("Client", Client::new)),
                Map.entry(8, new ButtonsHelper.EntityButtonAction("Clients Order", ClientsOrder::new)),
                Map.entry(9, new ButtonsHelper.EntityButtonAction("Company", Company::new)),
                Map.entry(10, new ButtonsHelper.EntityButtonAction("Container", Container::new)),
                Map.entry(11, new ButtonsHelper.EntityButtonAction("Employee", Employee::new)),
                Map.entry(12, new ButtonsHelper.EntityButtonAction("Fault Code", FaultCode::new)),
                Map.entry(13, new ButtonsHelper.EntityButtonAction("Harvest", Harvest::new)),
                Map.entry(14, new ButtonsHelper.EntityButtonAction("Machine", Machine::new)),
                Map.entry(15, new ButtonsHelper.EntityButtonAction("Machine Type", MachineType::new)),
                Map.entry(16, new ButtonsHelper.EntityButtonAction("Mix", Mix::new)),
                Map.entry(17, new ButtonsHelper.EntityButtonAction("Occupation", Occupation::new)),
                Map.entry(18, new ButtonsHelper.EntityButtonAction("Person", Person::new)),
                Map.entry(19, new ButtonsHelper.EntityButtonAction("Progress", Progress::new)),
                Map.entry(20, new ButtonsHelper.EntityButtonAction("Sort", Sort::new)),
                Map.entry(21, new ButtonsHelper.EntityButtonAction("Sweetness", Sweetness::new)),
                Map.entry(22, new ButtonsHelper.EntityButtonAction("Wine Type", WineType::new))
        );
    }
}
