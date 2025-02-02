package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Employee} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link Employee}.</li>
 *   <li>Добавяне на нов обект {@link Employee}.</li>
 *   <li>Актуализиране на съществуващ обект {@link Employee}.</li>
 *   <li>Изтриване на обект {@link Employee} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class EmployeeDao extends EntityDao<Employee> {

    /**
     * Конструира {@code EmployeeDao} за извършване на CRUD операции върху обекта {@link Employee}.
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