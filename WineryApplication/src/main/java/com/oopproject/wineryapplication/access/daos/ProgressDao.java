package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Progress;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Progress} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Progress} entities.</li>
 *   <li>Adding a new {@link Progress} entity.</li>
 *   <li>Updating an existing {@link Progress} entity.</li>
 *   <li>Deleting an {@link Progress} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class ProgressDao extends EntityDao<Progress> {

    /**
     * Constructs an {@code ProgressDao} for performing CRUD operations on the {@link Progress} entity.
     * {@inheritDoc}
     */
    public ProgressDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Progress get(int id) {
        try (Session session = createSession()) {
            return session.get(Progress.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Progress> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Progress", Progress.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Progress progress) {
        if (progress.getId() == null) {
            return insert(progress) != null;
        } else if (get(progress.getId()) == null) {
            return insert(progress) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Progress insert(Progress progress) {
        Progress newProgress = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newProgress = session.merge(progress);
            try {
                transaction.commit();
                return newProgress;
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
    public boolean update(int id, Progress progress) {
        if (get(id) != null) {
            progress.setId(id);
            return insert(progress) != null;
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
                Progress progress = session.get(Progress.class, id);
                if (progress != null) {
                    session.remove(progress);
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