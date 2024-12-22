package com.oopproject.wineryapplication.access.daos;
import com.access.daos.dao.EntityDao;
import com.access.entities.*;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ActDao extends EntityDao<Act> {

    public ActDao() {
        super();
    }

    @Override
    public Act get(int id) {
        try (Session session = createSession()) {
            return session.get(Act.class, id);
        }
    }

    @Override
    public List<Act> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Act", Act.class).list();
        }
    }

    @Override
    public boolean add(Act act) {
        if (act.getId() == null) {
            return insert(act) != null;
        }
        else if (get(act.getId()) == null) {
            return insert(act) != null;
        }
        return false;
    }

    @Override
    public Act insert(Act act) {
        Act newAct = null;
        try(Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newAct = session.merge(act);
            try {
                transaction.commit();
                return newAct;
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
    public boolean update(int id, Act act) {
        if (get(id) != null) {
            act.setId(id);
            return insert(act) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Act act = session.get(Act.class, id);
                if (act != null) {
                    session.remove(act);
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