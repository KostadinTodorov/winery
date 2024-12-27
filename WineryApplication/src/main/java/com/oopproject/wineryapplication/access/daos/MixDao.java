package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Mix;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MixDao extends EntityDao<Mix> {

    public MixDao() {
        super();
    }

    @Override
    public Mix get(int id) {
        try (Session session = createSession()) {
            return session.get(Mix.class, id);
        }
    }

    @Override
    public List<Mix> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Mix", Mix.class).list();
        }
    }

    @Override
    public boolean add(Mix mix) {
        if (mix.getId() == null) {
            return insert(mix) != null;
        } else if (get(mix.getId()) == null) {
            return insert(mix) != null;
        }
        return false;
    }

    @Override
    public Mix insert(Mix mix) {
        Mix newMix = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newMix = session.merge(mix);
            try {
                transaction.commit();
                return newMix;
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
    public boolean update(int id, Mix mix) {
        if (get(id) != null) {
            mix.setId(id);
            return insert(mix) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Mix mix = session.get(Mix.class, id);
                if (mix != null) {
                    session.remove(mix);
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