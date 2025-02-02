package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Occupation;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Occupation} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link Occupation}.</li>
 *   <li>Добавяне на нов обект {@link Occupation}.</li>
 *   <li>Актуализиране на съществуващ обект {@link Occupation}.</li>
 *   <li>Изтриване на обект {@link Occupation} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class OccupationDao extends EntityDao<Occupation> {

    /**
     * Конструира {@code OccupationDao} за извършване на CRUD операции върху обекта {@link Occupation}.
     * {@inheritDoc}
     */
    public OccupationDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Occupation get(int id) {
        try (Session session = createSession()) {
            return session.get(Occupation.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Occupation> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Occupation", Occupation.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Occupation occupation) {
        if (occupation.getId() == null) {
            return insert(occupation) != null;
        } else if (get(occupation.getId()) == null) {
            return insert(occupation) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Occupation insert(Occupation occupation) {
        Occupation newOccupation = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newOccupation = session.merge(occupation);
            try {
                transaction.commit();
                return newOccupation;
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
    public boolean update(int id, Occupation occupation) {
        if (get(id) != null) {
            occupation.setId(id);
            return insert(occupation) != null;
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
                Occupation occupation = session.get(Occupation.class, id);
                if (occupation != null) {
                    session.remove(occupation);
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