package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDao extends EntityDao<Employee> {

    public EmployeeDao() {
        super();
    }

    /**{@inheriteDoc}*/
    @Override
    public Employee get(int id) {
        try (Session session = createSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }

    @Override
    public boolean add(Employee employee) {
        if (employee.getId() == null) {
            return insert(employee) != null;
        } else if (get(employee.getId()) == null) {
            return insert(employee) != null;
        }
        return false;
    }

    @Override
    public Employee insert(Employee employee) {
        Employee newEmployee = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newEmployee = session.merge(employee);
            try {
                transaction.commit();
                return newEmployee;
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
    public boolean update(int id, Employee employee) {
        if (get(id) != null) {
            employee.setId(id);
            return insert(employee) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Employee employee = session.get(Employee.class, id);
                if (employee != null) {
                    session.remove(employee);
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