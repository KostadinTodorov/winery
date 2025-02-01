package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.BatchStoridge;
import com.oopproject.wineryapplication.access.entities.Behavior;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Behavior} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Behavior} entities.</li>
 *   <li>Adding a new {@link Behavior} entity.</li>
 *   <li>Updating an existing {@link Behavior} entity.</li>
 *   <li>Deleting an {@link Behavior} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class BehaviorDao extends EntityDao<Behavior> {

    /**
     * Constructs an {@code BehaviorDao} for performing CRUD operations on the {@link Behavior} entity.
     * {@inheritDoc}
     */
    public BehaviorDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Behavior get(int id) {
        try (Session session = createSession()) {
            return session.get(Behavior.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Behavior> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Behavior", Behavior.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Behavior behavior) {
        if (behavior.getId() == null) {
            return insert(behavior) != null;
        } else if (get(behavior.getId()) == null) {
            return insert(behavior) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Behavior insert(Behavior behavior) {
        Behavior newBehavior = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newBehavior = session.merge(behavior);
            try {
                transaction.commit();
                return newBehavior;
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
    public boolean update(int id, Behavior behavior) {
        if (get(id) != null) {
            behavior.setId(id);
            return insert(behavior) != null;
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
                Behavior behavior = session.get(Behavior.class, id);
                if (behavior != null) {
                    session.remove(behavior);
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