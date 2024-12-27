package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDao extends EntityDao<Client> {

    public ClientDao() {
        super();
    }

    @Override
    public Client get(int id) {
        try (Session session = createSession()) {
            return session.get(Client.class, id);
        }
    }

    @Override
    public List<Client> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }

    @Override
    public boolean add(Client client) {
        if (client.getId() == null) {
            return insert(client) != null;
        } else if (get(client.getId()) == null) {
            return insert(client) != null;
        }
        return false;
    }

    @Override
    public Client insert(Client client) {
        Client newClient = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newClient = session.merge(client);
            try {
                transaction.commit();
                return newClient;
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
    public boolean update(int id, Client client) {
        if (get(id) != null) {
            client.setId(id);
            return insert(client) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Client client = session.get(Client.class, id);
                if (client != null) {
                    session.remove(client);
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