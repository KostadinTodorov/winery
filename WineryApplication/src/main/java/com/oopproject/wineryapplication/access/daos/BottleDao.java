package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Behavior;
import com.oopproject.wineryapplication.access.entities.Bottle;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Bottle} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link Bottle}.</li>
 *   <li>Добавяне на нов обект {@link Bottle}.</li>
 *   <li>Актуализиране на съществуващ обект {@link Bottle}.</li>
 *   <li>Изтриване на обект {@link Bottle} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class BottleDao extends EntityDao<Bottle> {

    /**
     * Конструира {@code BottleDao} за извършване на CRUD операции върху обекта {@link Bottle}.
     * {@inheritDoc}
     */
    public BottleDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bottle get(int id) {
        try (Session session = createSession()) {
            return session.get(Bottle.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bottle> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Bottle", Bottle.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Bottle bottle) {
        if (bottle.getId() == null) {
            return insert(bottle) != null;
        } else if (get(bottle.getId()) == null) {
            return insert(bottle) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bottle insert(Bottle bottle) {
        Bottle newBottle = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newBottle = session.merge(bottle);
            try {
                transaction.commit();
                return newBottle;
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
    public boolean update(int id, Bottle bottle) {
        if (get(id) != null) {
            bottle.setId(id);
            return insert(bottle) != null;
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
                Bottle bottle = session.get(Bottle.class, id);
                if (bottle != null) {
                    session.remove(bottle);
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