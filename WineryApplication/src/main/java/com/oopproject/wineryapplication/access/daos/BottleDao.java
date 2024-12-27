package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Bottle;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BottleDao extends EntityDao<Bottle> {

    public BottleDao() {
        super();
    }

    @Override
    public Bottle get(int id) {
        try (Session session = createSession()) {
            return session.get(Bottle.class, id);
        }
    }

    @Override
    public List<Bottle> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Bottle", Bottle.class).list();
        }
    }

    @Override
    public boolean add(Bottle bottle) {
        if (bottle.getId() == null) {
            return insert(bottle) != null;
        } else if (get(bottle.getId()) == null) {
            return insert(bottle) != null;
        }
        return false;
    }

    @Override
    public Bottle insert(Bottle bottle) {
        Bottle newBottle = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newBottle = session.merge(bottle);
            try {
                transaction.commit();
                return newBottle;
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
    public boolean update(int id, Bottle bottle) {
        if (get(id) != null) {
            bottle.setId(id);
            return insert(bottle) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Bottle bottle = session.get(Bottle.class, id);
                if (bottle != null) {
                    session.remove(bottle);
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