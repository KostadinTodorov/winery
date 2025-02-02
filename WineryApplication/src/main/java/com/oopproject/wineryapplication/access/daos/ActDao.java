package com.oopproject.wineryapplication.access.daos;
import com.oopproject.wineryapplication.access.entities.Act;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import com.oopproject.wineryapplication.access.entities.Answer;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за изпълнение на CRUD операции върху {@link Act} чрез Hibernate.
 *
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * - Извличане на единични или множество {@link Act} обекти.
 * - Добавяне на нов {@link Act} обект.
 * - Актуализиране на съществуващ {@link Act} обект.
 * - Изтриване на {@link Act} обект по неговото ID.
 *
 * Всеки метод използва Hibernate сесии за взаимодействие с базата данни и включва
 * подходящо управление на транзакции за осигуряване на целостта на данните.
 */
public class ActDao extends EntityDao<Act> {

    /**
     * Конструира {@code ActDao} за изпълнение на CRUD операции върху {@link Act}.
     * {@inheritDoc}
     */
    public ActDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Act get(int id) {
        try (Session session = createSession()) {
            return session.get(Act.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Act> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Act", Act.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Act act) {
        if (act.getId() == null) {
            return insert(act) != null;
        }
        else if (get(act.getId()) == null) {
            return insert(act) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Act insert(Act act) {
        Act newAct = null;
        try(Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newAct = session.merge(act);
            try {
                transaction.commit();
                return newAct;
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
    public boolean update(int id, Act act) {
        if (get(id) != null) {
            act.setId(id);
            return insert(act) != null;
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
                Act act = session.get(Act.class, id);
                if (act != null) {
                    session.remove(act);
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