package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Person;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A DAO class for performing CRUD operations on the {@link Person} entity using Hibernate.
 * <p>
 * This class extends {@link EntityDao} and provides implementations for:
 * <ul>
 *   <li>Retrieving single or multiple {@link Person} entities.</li>
 *   <li>Adding a new {@link Person} entity.</li>
 *   <li>Updating an existing {@link Person} entity.</li>
 *   <li>Deleting an {@link Person} entity by its ID.</li>
 * </ul>
 * <p>
 * Each method utilizes Hibernate sessions for database interactions and includes
 * appropriate transaction handling to ensure data integrity.
 */
public class PersonDao extends EntityDao<Person> {

    /**
     * Constructs an {@code PersonDao} for performing CRUD operations on the {@link Person} entity.
     * {@inheritDoc}
     */
    public PersonDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person get(int id) {
        try (Session session = createSession()) {
            return session.get(Person.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Person", Person.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Person person) {
        if (person.getId() == null) {
            return insert(person) != null;
        } else if (get(person.getId()) == null) {
            return insert(person) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person insert(Person person) {
        Person newPerson = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newPerson = session.merge(person);
            try {
                transaction.commit();
                return newPerson;
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
    public boolean update(int id, Person person) {
        if (get(id) != null) {
            person.setId(id);
            return insert(person) != null;
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
                Person person = session.get(Person.class, id);
                if (person != null) {
                    session.remove(person);
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