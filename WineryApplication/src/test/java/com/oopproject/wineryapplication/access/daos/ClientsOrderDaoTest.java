package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.ClientsOrder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientsOrderDaoTest {

    @Test
    void testSingleGetItemWithExistingID() {
        ClientsOrderDao dao = new ClientsOrderDao();
        ClientsOrder c = new ClientsOrder();
        c = dao.get(6);
        assertNotNull(c, "=================>>>" + c.getId().toString());
    }

    @Test
    void testSingleGetItemWithNonExistingID() {
        ClientsOrderDao dao = new ClientsOrderDao();
        ClientsOrder c = new ClientsOrder();
        c = dao.get(1);
        assertNotNull(c, "=================>>>" + c.getId().toString());
    }


    @Test
    void testSingleGetItems() {
        ClientsOrderDao dao = new ClientsOrderDao();
        List<ClientsOrder> list = dao.getAll();
        assertNotNull(list);
    }
}