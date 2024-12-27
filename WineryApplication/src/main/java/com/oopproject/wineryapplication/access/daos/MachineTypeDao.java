package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.MachineType;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MachineTypeDao extends EntityDao<MachineType> {

    public MachineTypeDao() {
        super();
    }

    @Override
    public MachineType get(int id) {
        try (Session session = createSession()) {
            return session.get(MachineType.class, id);
        }
    }

    @Override
    public List<MachineType> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from MachineType", MachineType.class).list();
        }
    }

    @Override
    public boolean add(MachineType machineType) {
        if (machineType.getId() == null) {
            return insert(machineType) != null;
        } else if (get(machineType.getId()) == null) {
            return insert(machineType) != null;
        }
        return false;
    }

    @Override
    public MachineType insert(MachineType machineType) {
        MachineType newMachineType = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newMachineType = session.merge(machineType);
            try {
                transaction.commit();
                return newMachineType;
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
    public boolean update(int id, MachineType machineType) {
        if (get(id) != null) {
            machineType.setId(id);
            return insert(machineType) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                MachineType machineType = session.get(MachineType.class, id);
                if (machineType != null) {
                    session.remove(machineType);
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