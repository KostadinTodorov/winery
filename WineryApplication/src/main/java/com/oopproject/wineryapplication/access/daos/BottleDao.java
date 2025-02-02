package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Behavior;
import com.oopproject.wineryapplication.access.entities.Bottle;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Bottle} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Bottle} entities.</li>
 *   <li>Adding a new {@link Bottle} entity.</li>
 *   <li>Updating an existing {@link Bottle} entity.</li>
 *   <li>Deleting an {@link Bottle} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class BottleDao extends EntityDao<Bottle> {

    /**
     * Constructs an {@code BottleDao} for performing CRUD operations on the {@link Bottle} entity.
     * {@inheritDoc}
     */
    public BottleDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bottle get(int id) {
        try (Session session = createSession()) {
            return session.get(Bottle.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bottle> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Bottle", Bottle.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Bottle bottle) {
        if (bottle.getId() == null) {
            return insert(bottle) != null;
        } else if (get(bottle.getId()) == null) {
            return insert(bottle) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bottle insert(Bottle bottle) {
        Bottle newBottle = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newBottle = session.merge(bottle);
            try {
                transaction.commit();
                return newBottle;
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
    public boolean update(int id, Bottle bottle) {
        if (get(id) != null) {
            bottle.setId(id);
            return insert(bottle) != null;
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
                Bottle bottle = session.get(Bottle.class, id);
                if (bottle != null) {
                    session.remove(bottle);
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