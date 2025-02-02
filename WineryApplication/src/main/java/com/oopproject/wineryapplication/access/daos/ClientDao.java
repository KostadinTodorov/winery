package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Bottle;
import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Client} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Client} entities.</li>
 *   <li>Adding a new {@link Client} entity.</li>
 *   <li>Updating an existing {@link Client} entity.</li>
 *   <li>Deleting an {@link Client} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class ClientDao extends EntityDao<Client> {

    /**
     * Constructs an {@code ClientDao} for performing CRUD operations on the {@link Client} entity.
     * {@inheritDoc}
     */
    public ClientDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client get(int id) {
        try (Session session = createSession()) {
            return session.get(Client.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Client client) {
        if (client.getId() == null) {
            return insert(client) != null;
        } else if (get(client.getId()) == null) {
            return insert(client) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(int id, Client client) {
        if (get(id) != null) {
            client.setId(id);
            return insert(client) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
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