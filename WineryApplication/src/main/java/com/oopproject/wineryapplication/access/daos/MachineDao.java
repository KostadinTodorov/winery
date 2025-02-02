package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Machine;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Machine} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Machine} entities.</li>
 *   <li>Adding a new {@link Machine} entity.</li>
 *   <li>Updating an existing {@link Machine} entity.</li>
 *   <li>Deleting an {@link Machine} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class MachineDao extends EntityDao<Machine> {

    /**
     * Constructs an {@code MachineDao} for performing CRUD operations on the {@link Machine} entity.
     * {@inheritDoc}
     */
    public MachineDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Machine get(int id) {
        try (Session session = createSession()) {
            return session.get(Machine.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Machine> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Machine", Machine.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Machine machine) {
        if (machine.getId() == null) {
            return insert(machine) != null;
        } else if (get(machine.getId()) == null) {
            return insert(machine) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Machine insert(Machine machine) {
        Machine newMachine = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newMachine = session.merge(machine);
            try {
                transaction.commit();
                return newMachine;
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
    public boolean update(int id, Machine machine) {
        if (get(id) != null) {
            machine.setId(id);
            return insert(machine) != null;
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
                Machine machine = session.get(Machine.class, id);
                if (machine != null) {
                    session.remove(machine);
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