package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.WineType;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link WineType} с помощта на Hibernate.
 *
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * - Извличане на единични или множество обекти {@link WineType}.
 * - Добавяне на нов обект {@link WineType}.
 * - Актуализиране на съществуващ обект {@link WineType}.
 * - Изтриване на обект {@link WineType} по неговия ID.
 *
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class WineTypeDao extends EntityDao<WineType> {

    /**
     * Конструира {@code WineTypeDao} за извършване на CRUD операции върху обекта {@link WineType}.
     * {@inheritDoc}
     */
    public WineTypeDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WineType get(int id) {
        try (Session session = createSession()) {
            return session.get(WineType.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WineType> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from WineType", WineType.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(WineType wineType) {
        if (wineType.getId() == null) {
            return insert(wineType) != null;
        } else if (get(wineType.getId()) == null) {
            return insert(wineType) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WineType insert(WineType wineType) {
        WineType newWineType = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newWineType = session.merge(wineType);
            try {
                transaction.commit();
                return newWineType;
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
    public boolean update(int id, WineType wineType) {
        if (get(id) != null) {
            wineType.setId(id);
            return insert(wineType) != null;
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
                WineType wineType = session.get(WineType.class, id);
                if (wineType != null) {
                    session.remove(wineType);
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