package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Machine;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MachineDao extends EntityDao<Machine> {

    public MachineDao() {
        super();
    }

    @Override
    public Machine get(int id) {
        try (Session session = createSession()) {
            return session.get(Machine.class, id);
        }
    }

    @Override
    public List<Machine> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Machine", Machine.class).list();
        }
    }

    @Override
    public boolean add(Machine machine) {
        if (machine.getId() == null) {
            return insert(machine) != null;
        } else if (get(machine.getId()) == null) {
            return insert(machine) != null;
        }
        return false;
    }

    @Override
    public Machine insert(Machine machine) {
        Machine newMachine = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newMachine = session.merge(machine);
            try {
                transaction.commit();
                return newMachine;
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
    public boolean update(int id, Machine machine) {
        if (get(id) != null) {
            machine.setId(id);
            return insert(machine) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Machine machine = session.get(Machine.class, id);
                if (machine != null) {
                    session.remove(machine);
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