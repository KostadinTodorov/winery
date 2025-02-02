package com.oopproject.wineryapplication.access.daos.dao;

import com.oopproject.wineryapplication.access.entities.Act;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Конкретна имплементация на класа {@link EntityDao} за управление на обекти от специфичен тип.
 *
 * Този клас предоставя CRUD операции за обекти от тип {@code T}, където {@code T} е подклас
 * на {@link Entity}. Използва Hibernate за взаимодействия с базата данни. Специфичният тип на обекта,
 * управляван от това DAO, се определя от параметъра за тип {@code T} и свързания клас {@code type}.
 *
 * @param <T> типът на обекта, управляван от това DAO, разширяващ {@link Entity}
 */
public class TemplateDao<T extends Entity> extends EntityDao<T> {

    private final Class<T> type;

    /**
     * Конструира нов инстанция на {@code TemplateDao} за управление на обекти от посочения тип.
     *
     * @param type {@code Class} обект, представляващ специфичния тип на обект {@code T},
     *             управляем от тази инстанция на {@code TemplateDao}. {@code T} трябва да разширява {@code Entity}.
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
