package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Sweetness;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Sweetness} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Sweetness} entities.</li>
 *   <li>Adding a new {@link Sweetness} entity.</li>
 *   <li>Updating an existing {@link Sweetness} entity.</li>
 *   <li>Deleting an {@link Sweetness} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class SweetnessDao extends EntityDao<Sweetness> {

    /**
     * Constructs an {@code SweetnessDao} for performing CRUD operations on the {@link Sweetness} entity.
     * {@inheritDoc}
     */
    public SweetnessDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sweetness get(int id) {
        try (Session session = createSession()) {
            return session.get(Sweetness.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sweetness> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Sweetness", Sweetness.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Sweetness sweetness) {
        if (sweetness.getId() == null) {
            return insert(sweetness) != null;
        } else if (get(sweetness.getId()) == null) {
            return insert(sweetness) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sweetness insert(Sweetness sweetness) {
        Sweetness newSweetness = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newSweetness = session.merge(sweetness);
            try {
                transaction.commit();
                return newSweetness;
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
    public boolean update(int id, Sweetness sweetness) {
        if (get(id) != null) {
            sweetness.setId(id);
            return insert(sweetness) != null;
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
                Sweetness sweetness = session.get(Sweetness.class, id);
                if (sweetness != null) {
                    session.remove(sweetness);
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