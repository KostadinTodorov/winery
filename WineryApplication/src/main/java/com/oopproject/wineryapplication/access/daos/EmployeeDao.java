package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Employee} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Employee} entities.</li>
 *   <li>Adding a new {@link Employee} entity.</li>
 *   <li>Updating an existing {@link Employee} entity.</li>
 *   <li>Deleting an {@link Employee} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class EmployeeDao extends EntityDao<Employee> {

    /**
     * Constructs an {@code EmployeeDao} for performing CRUD operations on the {@link Employee} entity.
     * {@inheritDoc}
     */
    public EmployeeDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee get(int id) {
        try (Session session = createSession()) {
            return session.get(Employee.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Employee employee) {
        if (employee.getId() == null) {
            return insert(employee) != null;
        } else if (get(employee.getId()) == null) {
            return insert(employee) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(int id, Employee employee) {
        if (get(id) != null) {
            employee.setId(id);
            return insert(employee) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
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