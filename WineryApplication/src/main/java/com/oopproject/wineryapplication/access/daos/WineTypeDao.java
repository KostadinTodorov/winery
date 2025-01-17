package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.WineType;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WineTypeDao extends EntityDao<WineType> {

    public WineTypeDao() {
        super();
    }

    @Override
    public WineType get(int id) {
        try (Session session = createSession()) {
            return session.get(WineType.class, id);
        }
    }

    @Override
    public List<WineType> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from WineType", WineType.class).list();
        }
    }

    @Override
    public boolean add(WineType wineType) {
        if (wineType.getId() == null) {
            return insert(wineType) != null;
        } else if (get(wineType.getId()) == null) {
            return insert(wineType) != null;
        }
        return false;
    }

    @Override
    public WineType insert(WineType wineType) {
        WineType newWineType = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newWineType = session.merge(wineType);
            try {
                transaction.commit();
                return newWineType;
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
    public boolean update(int id, WineType wineType) {
        if (get(id) != null) {
            wineType.setId(id);
            return insert(wineType) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                WineType wineType = session.get(WineType.class, id);
                if (wineType != null) {
                    session.remove(wineType);
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