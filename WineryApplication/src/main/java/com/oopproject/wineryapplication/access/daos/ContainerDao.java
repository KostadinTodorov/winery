package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Container;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ContainerDao extends EntityDao<Container> {

    public ContainerDao() {
        super();
    }

    @Override
    public Container get(int id) {
        try (Session session = createSession()) {
            return session.get(Container.class, id);
        }
    }

    @Override
    public List<Container> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Container", Container.class).list();
        }
    }

    @Override
    public boolean add(Container container) {
        if (container.getId() == null) {
            return insert(container) != null;
        } else if (get(container.getId()) == null) {
            return insert(container) != null;
        }
        return false;
    }

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

    @Override
    public boolean update(int id, Container container) {
        if (get(id) != null) {
            container.setId(id);
            return insert(container) != null;
        }
        return false;
    }

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