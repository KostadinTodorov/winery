package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Progress;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Progress} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link Progress}.</li>
 *   <li>Добавяне на нов обект {@link Progress}.</li>
 *   <li>Актуализиране на съществуващ обект {@link Progress}.</li>
 *   <li>Изтриване на обект {@link Progress} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class ProgressDao extends EntityDao<Progress> {

    /**
     * Конструира {@code ProgressDao} за извършване на CRUD операции върху обекта {@link Progress}.
     * {@inheritDoc}
     */
    public ProgressDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Progress get(int id) {
        try (Session session = createSession()) {
            return session.get(Progress.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Progress> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Progress", Progress.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Progress progress) {
        if (progress.getId() == null) {
            return insert(progress) != null;
        } else if (get(progress.getId()) == null) {
            return insert(progress) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Progress insert(Progress progress) {
        Progress newProgress = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newProgress = session.merge(progress);
            try {
                transaction.commit();
                return newProgress;
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
    public boolean update(int id, Progress progress) {
        if (get(id) != null) {
            progress.setId(id);
            return insert(progress) != null;
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
                Progress progress = session.get(Progress.class, id);
                if (progress != null) {
                    session.remove(progress);
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