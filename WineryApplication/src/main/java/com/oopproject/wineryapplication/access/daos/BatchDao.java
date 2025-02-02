package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.daos.dao.Dao;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import com.oopproject.wineryapplication.access.entities.Answer;
import com.oopproject.wineryapplication.access.entities.Batch;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Batch} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link Batch}.</li>
 *   <li>Добавяне на нов обект {@link Batch}.</li>
 *   <li>Актуализиране на съществуващ обект {@link Batch}.</li>
 *   <li>Изтриване на обект {@link Batch} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class BatchDao extends EntityDao<Batch> {

    /**
     * Конструира {@code BatchDao} за извършване на CRUD операции върху обекта {@link Batch}.
     * {@inheritDoc}
     */
    public BatchDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Batch get(int id) {
        try (Session session = createSession()) {
            return session.get(Batch.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Batch> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Batch", Batch.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Batch batch) {
        if (batch.getId() == null) {
            return insert(batch) != null;
        }
        else if (get(batch.getId()) == null) {
            return insert(batch) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Batch insert(Batch batch) {
        Batch newBatch = null;
        try(Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newBatch = session.merge(batch);
            try {
                transaction.commit();
                return newBatch;
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
    public boolean update(int id, Batch batch) {
        if (get(id) != null) {
            batch.setId(id);
            return insert(batch) != null;
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
                Batch batch = session.get(Batch.class, id);
                if (batch != null) {
                    session.remove(batch);
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
