package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Occupation;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Occupation} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Occupation} entities.</li>
 *   <li>Adding a new {@link Occupation} entity.</li>
 *   <li>Updating an existing {@link Occupation} entity.</li>
 *   <li>Deleting an {@link Occupation} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class OccupationDao extends EntityDao<Occupation> {

    /**
     * Constructs an {@code OccupationDao} for performing CRUD operations on the {@link Occupation} entity.
     * {@inheritDoc}
     */
    public OccupationDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Occupation get(int id) {
        try (Session session = createSession()) {
            return session.get(Occupation.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Occupation> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Occupation", Occupation.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Occupation occupation) {
        if (occupation.getId() == null) {
            return insert(occupation) != null;
        } else if (get(occupation.getId()) == null) {
            return insert(occupation) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Occupation insert(Occupation occupation) {
        Occupation newOccupation = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newOccupation = session.merge(occupation);
            try {
                transaction.commit();
                return newOccupation;
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
    public boolean update(int id, Occupation occupation) {
        if (get(id) != null) {
            occupation.setId(id);
            return insert(occupation) != null;
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
                Occupation occupation = session.get(Occupation.class, id);
                if (occupation != null) {
                    session.remove(occupation);
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