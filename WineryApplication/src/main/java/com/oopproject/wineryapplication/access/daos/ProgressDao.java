package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Progress;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProgressDao extends EntityDao<Progress> {

    public ProgressDao() {
        super();
    }

    @Override
    public Progress get(int id) {
        try (Session session = createSession()) {
            return session.get(Progress.class, id);
        }
    }

    @Override
    public List<Progress> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Progress", Progress.class).list();
        }
    }

    @Override
    public boolean add(Progress progress) {
        if (progress.getId() == null) {
            return insert(progress) != null;
        } else if (get(progress.getId()) == null) {
            return insert(progress) != null;
        }
        return false;
    }

    @Override
    public Progress insert(Progress progress) {
        Progress newProgress = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newProgress = session.merge(progress);
            try {
                transaction.commit();
                return newProgress;
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
    public boolean update(int id, Progress progress) {
        if (get(id) != null) {
            progress.setId(id);
            return insert(progress) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Progress progress = session.get(Progress.class, id);
                if (progress != null) {
                    session.remove(progress);
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