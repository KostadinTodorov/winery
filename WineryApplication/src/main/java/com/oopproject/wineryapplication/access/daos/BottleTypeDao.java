package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.BottleType;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import com.oopproject.wineryapplication.access.entities.Client;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link BottleType} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link BottleType} entities.</li>
 *   <li>Adding a new {@link BottleType} entity.</li>
 *   <li>Updating an existing {@link BottleType} entity.</li>
 *   <li>Deleting an {@link BottleType} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class BottleTypeDao extends EntityDao<BottleType> {

    /**
     * Constructs an {@code BottleTypeDao} for performing CRUD operations on the {@link BottleType} entity.
     * {@inheritDoc}
     */
    public BottleTypeDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BottleType get(int id) {
        try (Session session = createSession()) {
            return session.get(BottleType.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BottleType> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from BottleType", BottleType.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(BottleType bottleType) {
        if (bottleType.getId() == null) {
            return insert(bottleType) != null;
        } else if (get(bottleType.getId()) == null) {
            return insert(bottleType) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BottleType insert(BottleType bottleType) {
        BottleType newBottleType = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newBottleType = session.merge(bottleType);
            try {
                transaction.commit();
                return newBottleType;
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
    public boolean update(int id, BottleType bottleType) {
        if (get(id) != null) {
            bottleType.setId(id);
            return insert(bottleType) != null;
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
                BottleType bottleType = session.get(BottleType.class, id);
                if (bottleType != null) {
                    session.remove(bottleType);
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