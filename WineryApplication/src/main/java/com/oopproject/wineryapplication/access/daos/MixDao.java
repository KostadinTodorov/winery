package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Mix;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за изпълнение на CRUD операции върху {@link Mix} чрез Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество {@link Mix} обекти.</li>
 *   <li>Добавяне на нов {@link Mix} обект.</li>
 *   <li>Актуализиране на съществуващ {@link Mix} обект.</li>
 *   <li>Изтриване на {@link Mix} обект по неговото ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействие с базата данни и включва
 * подходящо управление на транзакции за осигуряване на целостта на данните.
 */
public class MixDao extends EntityDao<Mix> {

    /**
     * Конструира {@code MixDao} за изпълнение на CRUD операции върху {@link Mix}.
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