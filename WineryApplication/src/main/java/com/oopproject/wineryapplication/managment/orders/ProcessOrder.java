package com.oopproject.wineryapplication.managment.orders;

import com.oopproject.wineryapplication.access.daos.ClientsOrderDao;
import com.oopproject.wineryapplication.access.entities.ClientsOrder;
import com.oopproject.wineryapplication.access.entities.Progress;
import org.hibernate.query.Order;

import java.time.LocalDate;
import java.util.Date;

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

    boolean updateOrder(Progress progress){
        order.setProgress(progress);
        try {
            return new ClientsOrderDao().insert(order) != null;
        } catch (Exception e) {
            return false;
        }
    }

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