package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Bottle;
import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за изпълнение на CRUD операции върху {@link Client} чрез Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество {@link Client} обекти.</li>
 *   <li>Добавяне на нов {@link Client} обект.</li>
 *   <li>Актуализиране на съществуващ {@link Client} обект.</li>
 *   <li>Изтриване на {@link Client} обект по неговото ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействие с базата данни и включва
 * подходящо управление на транзакции за осигуряване на целостта на данните.
 */
public class ClientDao extends EntityDao<Client> {

    /**
     * Конструира {@code ClientDao} за изпълнение на CRUD операции върху {@link Client}.
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