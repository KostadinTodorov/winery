package com.oopproject.wineryapplication.managment.orders;

import com.oopproject.wineryapplication.access.daos.ClientsOrderDao;
import com.oopproject.wineryapplication.access.entities.ClientsOrder;
import com.oopproject.wineryapplication.access.entities.Progress;
import org.hibernate.query.Order;

import java.time.LocalDate;
import java.util.Date;

/**
 * Клас {@code ProcessOrder} обработва поръчките от клиенти и управлява свързани процеси,
 * като актуализация на прогреса и завършване на поръчки.
 */
public class ProcessOrder {
    ClientsOrder order;
    OrderRequirements orderRequirements;
    public ProcessOrder(ClientsOrder order) {
        if (order.getId() != null) {
            this.order = order;
            this.orderRequirements = new OrderRequirements(order);
        }
        throw new IllegalArgumentException("Order ID cannot be null");
    }

    /**
     * Актуализира прогреса на текущата поръчка и записва промените в базата данни.
     * <p>
     * Методът задава новото състояние на прогреса на поръчката чрез {@link ClientsOrder#setProgress(Progress)}
     * и прави опит да го запише с помощта на {@link ClientsOrderDao#insert(ClientsOrder)}.
     * Ако операцията по запис не успее (например хвърля се изключение), методът ще върне {@code false}.
     *
     * @param progress Обект от тип {@link Progress}, който представлява новото състояние на прогреса за поръчката.
     *                 Този параметър не може да бъде {@code null} и се използва за обновяване на текущата поръчка.
     *
     * @return {@code true}, ако актуализацията на прогреса е записана успешно в базата данни;
     *         {@code false}, ако се появи грешка при запис или операцията е неуспешна.
     */
    boolean updateOrder(Progress progress){
        order.setProgress(progress);
        try {
            return new ClientsOrderDao().insert(order) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Завършва текущата поръчка, като задава дата на завършване и записва промените в базата данни.
     * <p>
     * Този метод използва {@link ClientsOrder#setCompletionDate(LocalDate)} за задаване на дата
     * на завършване и {@link ClientsOrderDao#insert(ClientsOrder)} за съхранение на актуализираната
     * информация в базата данни. Ако записването в базата данни е успешно, методът връща {@code true},
     * в противен случай връща {@code false}.
     *
     * @param date Дата, на която поръчката е завършена. Тя се задава чрез
     *             {@link ClientsOrder#setCompletionDate(LocalDate)}.
     * @return {@code true}, ако поръчката е успешно записана в базата данни;
     *         {@code false}, ако е възникнала грешка по време на записването.
     */
    boolean completeOrder(LocalDate date){
        order.setCompletionDate(date);
        try {
            return new ClientsOrderDao().insert(order) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public OrderRequirements getOrderRequirements() {
        return orderRequirements;
    }

    public void testdisplay() {
    }

//    public void setOrderRequirements(OrderRequirements orderRequirements) {
//        this.orderRequirements = orderRequirements;
//    }
}