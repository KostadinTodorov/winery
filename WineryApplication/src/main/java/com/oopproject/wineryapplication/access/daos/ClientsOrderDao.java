package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.ClientsOrder;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientsOrderDao extends EntityDao<ClientsOrder> {

    public ClientsOrderDao() {
        super();
    }

    @Override
    public ClientsOrder get(int id) {
        try (Session session = createSession()) {
            return session.get(ClientsOrder.class, id);
        }
    }

    @Override
    public List<ClientsOrder> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from ClientsOrder", ClientsOrder.class).list();
        }
    }

    @Override
    public boolean add(ClientsOrder clientsOrder) {
        if (clientsOrder.getId() == null) {
            return insert(clientsOrder) != null;
        } else if (get(clientsOrder.getId()) == null) {
            return insert(clientsOrder) != null;
        }
        return false;
    }

    @Override
    public ClientsOrder insert(ClientsOrder clientsOrder) {
        ClientsOrder newClientsOrder = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newClientsOrder = session.merge(clientsOrder);
            try {
                transaction.commit();
                return newClientsOrder;
            } catch (RollbackException e) {
                transaction.rollback();
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(int id, ClientsOrder clientsOrder) {
        if (get(id) != null) {
            clientsOrder.setId(id);
            return insert(clientsOrder) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                ClientsOrder clientsOrder = session.get(ClientsOrder.class, id);
                if (clientsOrder != null) {
                    session.remove(clientsOrder);
                    transaction.commit();
                    return true;
                } else {
                    return false;
                }
            } catch (RollbackException e) {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}