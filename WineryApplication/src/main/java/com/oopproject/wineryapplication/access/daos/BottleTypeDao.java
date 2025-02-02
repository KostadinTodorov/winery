package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.BottleType;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import com.oopproject.wineryapplication.access.entities.Client;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link BottleType} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link BottleType}.</li>
 *   <li>Добавяне на нов обект {@link BottleType}.</li>
 *   <li>Актуализиране на съществуващ обект {@link BottleType}.</li>
 *   <li>Изтриване на обект {@link BottleType} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class BottleTypeDao extends EntityDao<BottleType> {

    /**
     * Конструира {@code BottleTypeDao} за извършване на CRUD операции върху обекта {@link BottleType}.
     * {@inheritDoc}
     */
    public BottleTypeDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BottleType get(int id) {
        try (Session session = createSession()) {
            return session.get(BottleType.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BottleType> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from BottleType", BottleType.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(BottleType bottleType) {
        if (bottleType.getId() == null) {
            return insert(bottleType) != null;
        } else if (get(bottleType.getId()) == null) {
            return insert(bottleType) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BottleType insert(BottleType bottleType) {
        BottleType newBottleType = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newBottleType = session.merge(bottleType);
            try {
                transaction.commit();
                return newBottleType;
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
    public boolean update(int id, BottleType bottleType) {
        if (get(id) != null) {
            bottleType.setId(id);
            return insert(bottleType) != null;
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
                BottleType bottleType = session.get(BottleType.class, id);
                if (bottleType != null) {
                    session.remove(bottleType);
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