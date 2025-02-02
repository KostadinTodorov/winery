package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Container;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за изпълнение на CRUD операции върху {@link Container} чрез Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество {@link Container} обекти.</li>
 *   <li>Добавяне на нов {@link Container} обект.</li>
 *   <li>Актуализиране на съществуващ {@link Container} обект.</li>
 *   <li>Изтриване на {@link Container} обект по неговото ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействие с базата данни и включва
 * подходящо управление на транзакции за осигуряване на целостта на данните.
 */
public class ContainerDao extends EntityDao<Container> {

    /**
     * Конструира {@code ContainerDao} за изпълнение на CRUD операции върху {@link Container}.
     * {@inheritDoc}
     */
    public ContainerDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Container get(int id) {
        try (Session session = createSession()) {
            return session.get(Container.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Container> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Container", Container.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Container container) {
        if (container.getId() == null) {
            return insert(container) != null;
        } else if (get(container.getId()) == null) {
            return insert(container) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Container insert(Container container) {
        Container newContainer = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newContainer = session.merge(container);
            try {
                transaction.commit();
                return newContainer;
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
    public boolean update(int id, Container container) {
        if (get(id) != null) {
            container.setId(id);
            return insert(container) != null;
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
                Container container = session.get(Container.class, id);
                if (container != null) {
                    session.remove(container);
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