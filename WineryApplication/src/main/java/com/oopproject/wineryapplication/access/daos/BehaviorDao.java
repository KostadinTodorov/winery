package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Behavior;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BehaviorDao extends EntityDao<Behavior> {

    public BehaviorDao() {
        super();
    }

    @Override
    public Behavior get(int id) {
        try (Session session = createSession()) {
            return session.get(Behavior.class, id);
        }
    }

    @Override
    public List<Behavior> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Behavior", Behavior.class).list();
        }
    }

    @Override
    public boolean add(Behavior behavior) {
        if (behavior.getId() == null) {
            return insert(behavior) != null;
        } else if (get(behavior.getId()) == null) {
            return insert(behavior) != null;
        }
        return false;
    }

    @Override
    public Behavior insert(Behavior behavior) {
        Behavior newBehavior = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newBehavior = session.merge(behavior);
            try {
                transaction.commit();
                return newBehavior;
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
    public boolean update(int id, Behavior behavior) {
        if (get(id) != null) {
            behavior.setId(id);
            return insert(behavior) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Behavior behavior = session.get(Behavior.class, id);
                if (behavior != null) {
                    session.remove(behavior);
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