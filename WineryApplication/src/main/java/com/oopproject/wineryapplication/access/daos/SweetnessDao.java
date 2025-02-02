package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Sweetness;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Sweetness} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link Sweetness}.</li>
 *   <li>Добавяне на нов обект {@link Sweetness}.</li>
 *   <li>Актуализиране на съществуващ обект {@link Sweetness}.</li>
 *   <li>Изтриване на обект {@link Sweetness} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class SweetnessDao extends EntityDao<Sweetness> {

    /**
     * Конструира {@code SweetnessDao} за извършване на CRUD операции върху обекта {@link Sweetness}.
     * {@inheritDoc}
     */
    public SweetnessDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sweetness get(int id) {
        try (Session session = createSession()) {
            return session.get(Sweetness.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sweetness> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Sweetness", Sweetness.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Sweetness sweetness) {
        if (sweetness.getId() == null) {
            return insert(sweetness) != null;
        } else if (get(sweetness.getId()) == null) {
            return insert(sweetness) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sweetness insert(Sweetness sweetness) {
        Sweetness newSweetness = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newSweetness = session.merge(sweetness);
            try {
                transaction.commit();
                return newSweetness;
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
    public boolean update(int id, Sweetness sweetness) {
        if (get(id) != null) {
            sweetness.setId(id);
            return insert(sweetness) != null;
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
                Sweetness sweetness = session.get(Sweetness.class, id);
                if (sweetness != null) {
                    session.remove(sweetness);
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