package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.BatchStoridge;
import com.oopproject.wineryapplication.access.entities.Behavior;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за изпълнение на CRUD операции върху {@link Behavior} чрез Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество {@link Behavior} обекти.</li>
 *   <li>Добавяне на нов {@link Behavior} обект.</li>
 *   <li>Актуализиране на съществуващ {@link Behavior} обект.</li>
 *   <li>Изтриване на {@link Behavior} обект по неговото ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействие с базата данни и включва
 * подходящо управление на транзакции за осигуряване на целостта на данните.
 */
public class BehaviorDao extends EntityDao<Behavior> {

    /**
     * Конструира {@code BehaviorDao} за изпълнение на CRUD операции върху {@link Behavior}.
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