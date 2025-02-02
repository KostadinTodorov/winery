package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Harvest;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за изпълнение на CRUD операции върху {@link Harvest} чрез Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество {@link Harvest} обекти.</li>
 *   <li>Добавяне на нов {@link Harvest} обект.</li>
 *   <li>Актуализиране на съществуващ {@link Harvest} обект.</li>
 *   <li>Изтриване на {@link Harvest} обект по неговото ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействие с базата данни и включва
 * подходящо управление на транзакции за осигуряване на целостта на данните.
 */
public class HarvestDao extends EntityDao<Harvest> {

    /**
     * Конструира {@code HarvestDao} за изпълнение на CRUD операции върху {@link Harvest}.
     * {@inheritDoc}
     */
    public HarvestDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Harvest get(int id) {
        try (Session session = createSession()) {
            return session.get(Harvest.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Harvest> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Harvest", Harvest.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Harvest harvest) {
        if (harvest.getId() == null) {
            return insert(harvest) != null;
        } else if (get(harvest.getId()) == null) {
            return insert(harvest) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Harvest insert(Harvest harvest) {
        Harvest newHarvest = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newHarvest = session.merge(harvest);
            try {
                transaction.commit();
                return newHarvest;
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
    public boolean update(int id, Harvest harvest) {
        if (get(id) != null) {
            harvest.setId(id);
            return insert(harvest) != null;
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
                Harvest harvest = session.get(Harvest.class, id);
                if (harvest != null) {
                    session.remove(harvest);
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