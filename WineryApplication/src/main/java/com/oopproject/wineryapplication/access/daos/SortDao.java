package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Sort;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Sort} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link Sort}.</li>
 *   <li>Добавяне на нов обект {@link Sort}.</li>
 *   <li>Актуализиране на съществуващ обект {@link Sort}.</li>
 *   <li>Изтриване на обект {@link Sort} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class SortDao extends EntityDao<Sort> {

    /**
     * Конструира {@code SortDao} за извършване на CRUD операции върху обекта {@link Sort}.
     * {@inheritDoc}
     */
    public SortDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sort get(int id) {
        try (Session session = createSession()) {
            return session.get(Sort.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sort> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Sort", Sort.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Sort sort) {
        if (sort.getId() == null) {
            return insert(sort) != null;
        } else if (get(sort.getId()) == null) {
            return insert(sort) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sort insert(Sort sort) {
        Sort newSort = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newSort = session.merge(sort);
            try {
                transaction.commit();
                return newSort;
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
    public boolean update(int id, Sort sort) {
        if (get(id) != null) {
            sort.setId(id);
            return insert(sort) != null;
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
                Sort sort = session.get(Sort.class, id);
                if (sort != null) {
                    session.remove(sort);
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