package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.BottleType;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BottleTypeDao extends EntityDao<BottleType> {

    public BottleTypeDao() {
        super();
    }

    @Override
    public BottleType get(int id) {
        try (Session session = createSession()) {
            return session.get(BottleType.class, id);
        }
    }

    @Override
    public List<BottleType> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from BottleType", BottleType.class).list();
        }
    }

    @Override
    public boolean add(BottleType bottleType) {
        if (bottleType.getId() == null) {
            return insert(bottleType) != null;
        } else if (get(bottleType.getId()) == null) {
            return insert(bottleType) != null;
        }
        return false;
    }

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

    @Override
    public boolean update(int id, BottleType bottleType) {
        if (get(id) != null) {
            bottleType.setId(id);
            return insert(bottleType) != null;
        }
        return false;
    }

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