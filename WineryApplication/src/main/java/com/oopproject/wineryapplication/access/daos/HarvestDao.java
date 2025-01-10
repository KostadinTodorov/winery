package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Harvest;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HarvestDao extends EntityDao<Harvest> {

    public HarvestDao() {
        super();
    }

    @Override
    public Harvest get(int id) {
        try (Session session = createSession()) {
            return session.get(Harvest.class, id);
        }
    }

    @Override
    public List<Harvest> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Harvest", Harvest.class).list();
        }
    }

    @Override
    public boolean add(Harvest harvest) {
        if (harvest.getId() == null) {
            return insert(harvest) != null;
        } else if (get(harvest.getId()) == null) {
            return insert(harvest) != null;
        }
        return false;
    }

    @Override
    public Harvest insert(Harvest harvest) {
        Harvest newHarvest = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newHarvest = session.merge(harvest);
            try {
                transaction.commit();
                return newHarvest;
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
    public boolean update(int id, Harvest harvest) {
        if (get(id) != null) {
            harvest.setId(id);
            return insert(harvest) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Harvest harvest = session.get(Harvest.class, id);
                if (harvest != null) {
                    session.remove(harvest);
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