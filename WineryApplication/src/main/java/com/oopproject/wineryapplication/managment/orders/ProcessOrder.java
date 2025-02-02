package com.oopproject.wineryapplication.managment.orders;

import com.oopproject.wineryapplication.access.daos.ClientsOrderDao;
import com.oopproject.wineryapplication.access.entities.ClientsOrder;
import com.oopproject.wineryapplication.access.entities.Progress;
import org.hibernate.query.Order;

import java.time.LocalDate;
import java.util.Date;

/**
 * The ProcessOrder class is responsible for handling operations related to a client's order,
 * including updating the order progress, marking the order as complete, and retrieving order requirements.
 *
 * This class acts as a bridge between the ClientsOrder entity and its business logic,
 * performing validations and ensuring proper modifications are made to the order data.
 */
public class ProcessOrder {
    ClientsOrder order;
    OrderRequirements orderRequirements;


    public ProcessOrder(ClientsOrder order) {
        if (order == null || order.getId() == null) {
            throw new IllegalArgumentException("Order or Order ID cannot be null");
        }
        this.order = order;
        this.orderRequirements = new OrderRequirements(order);
    }

    public boolean updateOrder(Progress progress){
        order.setProgress(progress);
        try {
            return new ClientsOrderDao().insert(order) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean completeOrder(LocalDate date){
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
        System.out.println("===========wcddwcdwcdcd===================wdcwdcccd=================wcdwc========");
    }

//    public void setOrderRequirements(OrderRequirements orderRequirements) {
//        this.orderRequirements = orderRequirements;
//    }
}