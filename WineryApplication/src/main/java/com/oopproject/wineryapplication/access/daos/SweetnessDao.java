package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Sweetness;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SweetnessDao extends EntityDao<Sweetness> {

    public SweetnessDao() {
        super();
    }

    @Override
    public Sweetness get(int id) {
        try (Session session = createSession()) {
            return session.get(Sweetness.class, id);
        }
    }

    @Override
    public List<Sweetness> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Sweetness", Sweetness.class).list();
        }
    }

    @Override
    public boolean add(Sweetness sweetness) {
        if (sweetness.getId() == null) {
            return insert(sweetness) != null;
        } else if (get(sweetness.getId()) == null) {
            return insert(sweetness) != null;
        }
        return false;
    }

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

    @Override
    public boolean update(int id, Sweetness sweetness) {
        if (get(id) != null) {
            sweetness.setId(id);
            return insert(sweetness) != null;
        }
        return false;
    }

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