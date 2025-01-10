package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.FaultCode;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FaultCodeDao extends EntityDao<FaultCode> {

    public FaultCodeDao() {
        super();
    }

    @Override
    public FaultCode get(int id) {
        try (Session session = createSession()) {
            return session.get(FaultCode.class, id);
        }
    }

    @Override
    public List<FaultCode> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from FaultCode", FaultCode.class).list();
        }
    }

    @Override
    public boolean add(FaultCode faultCode) {
        if (faultCode.getId() == null) {
            return insert(faultCode) != null;
        } else if (get(faultCode.getId()) == null) {
            return insert(faultCode) != null;
        }
        return false;
    }

    @Override
    public FaultCode insert(FaultCode faultCode) {
        FaultCode newFaultCode = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newFaultCode = session.merge(faultCode);
            try {
                transaction.commit();
                return newFaultCode;
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
    public boolean update(int id, FaultCode faultCode) {
        if (get(id) != null) {
            faultCode.setId(id);
            return insert(faultCode) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                FaultCode faultCode = session.get(FaultCode.class, id);
                if (faultCode != null) {
                    session.remove(faultCode);
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