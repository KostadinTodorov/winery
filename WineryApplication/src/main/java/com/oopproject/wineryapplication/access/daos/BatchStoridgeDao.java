package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.BatchStoridge;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BatchStoridgeDao extends EntityDao<BatchStoridge> {

    public BatchStoridgeDao() {
        super();
    }

    @Override
    public BatchStoridge get(int id) {
        try (Session session = createSession()) {
            return session.get(BatchStoridge.class, id);
        }
    }

    @Override
    public List<BatchStoridge> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from BatchStoridge", BatchStoridge.class).list();
        }
    }

    @Override
    public boolean add(BatchStoridge batchStoridge) {
        if (batchStoridge.getId() == null) {
            return insert(batchStoridge) != null;
        } else if (get(batchStoridge.getId()) == null) {
            return insert(batchStoridge) != null;
        }
        return false;
    }

    @Override
    public BatchStoridge insert(BatchStoridge batchStoridge) {
        BatchStoridge newBatchStoridge = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newBatchStoridge = session.merge(batchStoridge);
            try {
                transaction.commit();
                return newBatchStoridge;
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
    public boolean update(int id, BatchStoridge batchStoridge) {
        if (get(id) != null) {
            batchStoridge.setId(id);
            return insert(batchStoridge) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                BatchStoridge batchStoridge = session.get(BatchStoridge.class, id);
                if (batchStoridge != null) {
                    session.remove(batchStoridge);
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