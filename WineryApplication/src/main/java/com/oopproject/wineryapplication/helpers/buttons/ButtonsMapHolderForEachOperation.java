package com.oopproject.wineryapplication.helpers.buttons;

import com.oopproject.wineryapplication.helpers.logger.LoggerHelper;
import com.oopproject.wineryapplication.helpers.logger.LoggerLevels;

import java.util.Map;

/**
 * Класът {@code ButtonsMapHolderForEachOperation} е конкретна реализация на абстрактния клас {@link ButtonsMapBase}
 * и предоставя карта, която свързва индекси с конкретни действия на бутоните, специфични за операции.
 * <p>
 * Този клас дефинира метод за инициализация на картата {@link #initializeMap()}, който настройва действията
 * на бутоните, свързани със следните операции:
 * </p>
 * <ul>
 *   <li>Поръчки</li>
 *   <li>Производство</li>
 *   <li>Бутилиране</li>
 *   <li>Покупки</li>
 * </ul>
 *
 * <p>Картата е създадена с помощта на {@link Map#ofEntries}, за да осигури неизменима структура,
 * където всеки запис е свързан с уникален ключ (целочислен индекс) и съответно действие {@link ButtonsHelper.OperationButtonAction}.</p>
 *
 * <p>В допълнение, по време на инициализационния процес се извежда информационно съобщение
 * чрез {@link LoggerHelper#logData(Class, LoggerLevels, String)}, което указва успешно създаване
 * на необходимите обекти.</p>
 *
 * @see ButtonsMapBase
 * @see ButtonsHelper.OperationButtonAction
 */
public class ButtonsMapHolderForEachOperation extends ButtonsMapBase{

    /**
     * Инициализира и връща карта от индекси към специфични действия на бутоните,
     * характерни за съответните операции. Тази карта се използва за определяне на действията,
     * асоциирани с отделни бутони в текущата операция.
     * <p>
     * Картата логически обвързва индекси с екземпляри на {@link ButtonsHelper.OperationButtonAction},
     * които представляват действията, свързани с конкретните операции като
     * {@code "Orders"}, {@code "Production"}, {@code "Bottling"} и {@code "Purchases"}.
     * <p>
     * По време на изпълнение методът също така записва логове за създаването
     * на тези нотификационни бутони с помощта на {@link LoggerHelper#logData(Class, LoggerLevels, String)}.
     *
     * @return {@inheritDoc} Картата {@code Map<Integer, ButtonsHelper.ButtonAction>} с индекси
     *         и съответните действия на бутоните. Ключовете представляват индексите,
     *         като например {@code 0}, {@code 1}, {@code 2} и {@code 3}, докато стойностите
     *         представляват действията, дефинирани чрез {@link ButtonsHelper.OperationButtonAction}.
     */
    @Override
    protected Map<Integer, ButtonsHelper.ButtonAction> initializeMap() {
        LoggerHelper.logData(ButtonsMapHolderForEachOperation.class, LoggerLevels.INFO, "Creation of the notification buttons");
        return Map.ofEntries(
                Map.entry(0, new ButtonsHelper.OperationButtonAction("Orders")),
                Map.entry(1, new ButtonsHelper.OperationButtonAction("Production")),
                Map.entry(2, new ButtonsHelper.OperationButtonAction("Bottling")),
                Map.entry(3, new ButtonsHelper.OperationButtonAction("Purchases"))
        );
    }
}
