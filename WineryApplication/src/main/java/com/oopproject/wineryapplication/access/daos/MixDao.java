package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Mix;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Mix} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Mix} entities.</li>
 *   <li>Adding a new {@link Mix} entity.</li>
 *   <li>Updating an existing {@link Mix} entity.</li>
 *   <li>Deleting an {@link Mix} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class MixDao extends EntityDao<Mix> {

    /**
     * Constructs an {@code MixDao} for performing CRUD operations on the {@link Mix} entity.
     * {@inheritDoc}
     */
    public MixDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mix get(int id) {
        try (Session session = createSession()) {
            return session.get(Mix.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mix> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Mix", Mix.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Mix mix) {
        if (mix.getId() == null) {
            return insert(mix) != null;
        } else if (get(mix.getId()) == null) {
            return insert(mix) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mix insert(Mix mix) {
        Mix newMix = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newMix = session.merge(mix);
            try {
                transaction.commit();
                return newMix;
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
    public boolean update(int id, Mix mix) {
        if (get(id) != null) {
            mix.setId(id);
            return insert(mix) != null;
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
                Mix mix = session.get(Mix.class, id);
                if (mix != null) {
                    session.remove(mix);
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