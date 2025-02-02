package com.oopproject.wineryapplication.access.daos.dao;

import com.oopproject.wineryapplication.access.entities.Act;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A concrete implementation of the {@link EntityDao} class for managing entities of a specific type.
 *
 * This class provides CRUD operations for entities of type {@code T}, where {@code T} is a subclass
 * of {@link Entity}. It utilizes Hibernate for database interactions. The specific type of entity
 * managed by this DAO is determined by the type parameter {@code T} and the associated class {@code type}.
 *
 * @param <T> the type of entity managed by this DAO, extending {@link Entity}
 */
public class TemplateDao<T extends Entity> extends EntityDao<T> {

    private final Class<T> type;

    /**
     * Constructs a new instance of {@code TemplateDao} for managing entities of the specified type.
     *
     * @param type the {@code Class} object representing the specific type of entity {@code T}
     *             managed by this instance of the {@code TemplateDao}. {@code T} must extend {@code Entity}.
     */
    public TemplateDao(Class<T> type) {
        super();
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int id) {
        try (Session session = createSession()) {
            return session.get(type, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from " + type.getName(), type).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T entity) {
        if (entity.getId() == null) {
            return insert(entity) != null;
        }
        else if (get(entity.getId()) == null) {
            return insert(entity) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T insert(T entity) {
        T newEntity = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newEntity = session.merge(entity);
            try {
                transaction.commit();
                return newEntity;
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
    public boolean update(int id, T entity) {
        if (get(id) != null) {
            entity.setId(id);
            return insert(entity) != null;
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
                T entity = session.get(type, id);
                if (entity != null) {
                    session.remove(entity);
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
