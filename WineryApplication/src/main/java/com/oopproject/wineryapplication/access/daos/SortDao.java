package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Sort;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Sort} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Sort} entities.</li>
 *   <li>Adding a new {@link Sort} entity.</li>
 *   <li>Updating an existing {@link Sort} entity.</li>
 *   <li>Deleting an {@link Sort} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class SortDao extends EntityDao<Sort> {

    /**
     * Constructs an {@code SortDao} for performing CRUD operations on the {@link Sort} entity.
     * {@inheritDoc}
     */
    public SortDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sort get(int id) {
        try (Session session = createSession()) {
            return session.get(Sort.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sort> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Sort", Sort.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Sort sort) {
        if (sort.getId() == null) {
            return insert(sort) != null;
        } else if (get(sort.getId()) == null) {
            return insert(sort) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sort insert(Sort sort) {
        Sort newSort = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newSort = session.merge(sort);
            try {
                transaction.commit();
                return newSort;
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
    public boolean update(int id, Sort sort) {
        if (get(id) != null) {
            sort.setId(id);
            return insert(sort) != null;
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
                Sort sort = session.get(Sort.class, id);
                if (sort != null) {
                    session.remove(sort);
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