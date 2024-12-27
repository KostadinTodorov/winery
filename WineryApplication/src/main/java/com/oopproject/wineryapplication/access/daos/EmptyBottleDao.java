package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.EmptyBottle;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmptyBottleDao extends EntityDao<EmptyBottle> {

    public EmptyBottleDao() {
        super();
    }

    @Override
    public EmptyBottle get(int id) {
        try (Session session = createSession()) {
            return session.get(EmptyBottle.class, id);
        }
    }

    @Override
    public List<EmptyBottle> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from EmptyBottle", EmptyBottle.class).list();
        }
    }

    @Override
    public boolean add(EmptyBottle emptyBottle) {
        if (emptyBottle.getId() == null) {
            return insert(emptyBottle) != null;
        } else if (get(emptyBottle.getId()) == null) {
            return insert(emptyBottle) != null;
        }
        return false;
    }

    @Override
    public EmptyBottle insert(EmptyBottle emptyBottle) {
        EmptyBottle newEmptyBottle = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newEmptyBottle = session.merge(emptyBottle);
            try {
                transaction.commit();
                return newEmptyBottle;
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
    public boolean update(int id, EmptyBottle emptyBottle) {
        if (get(id) != null) {
            emptyBottle.setId(id);
            return insert(emptyBottle) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                EmptyBottle emptyBottle = session.get(EmptyBottle.class, id);
                if (emptyBottle != null) {
                    session.remove(emptyBottle);
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