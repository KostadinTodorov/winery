package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Occupation;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OccupationDao extends EntityDao<Occupation> {

    public OccupationDao() {
        super();
    }

    @Override
    public Occupation get(int id) {
        try (Session session = createSession()) {
            return session.get(Occupation.class, id);
        }
    }

    @Override
    public List<Occupation> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Occupation", Occupation.class).list();
        }
    }

    @Override
    public boolean add(Occupation occupation) {
        if (occupation.getId() == null) {
            return insert(occupation) != null;
        } else if (get(occupation.getId()) == null) {
            return insert(occupation) != null;
        }
        return false;
    }

    @Override
    public Occupation insert(Occupation occupation) {
        Occupation newOccupation = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newOccupation = session.merge(occupation);
            try {
                transaction.commit();
                return newOccupation;
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
    public boolean update(int id, Occupation occupation) {
        if (get(id) != null) {
            occupation.setId(id);
            return insert(occupation) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Occupation occupation = session.get(Occupation.class, id);
                if (occupation != null) {
                    session.remove(occupation);
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