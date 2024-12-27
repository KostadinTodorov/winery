package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Person;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonDao extends EntityDao<Person> {

    public PersonDao() {
        super();
    }

    @Override
    public Person get(int id) {
        try (Session session = createSession()) {
            return session.get(Person.class, id);
        }
    }

    @Override
    public List<Person> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Person", Person.class).list();
        }
    }

    @Override
    public boolean add(Person person) {
        if (person.getId() == null) {
            return insert(person) != null;
        } else if (get(person.getId()) == null) {
            return insert(person) != null;
        }
        return false;
    }

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

    @Override
    public boolean update(int id, Person person) {
        if (get(id) != null) {
            person.setId(id);
            return insert(person) != null;
        }
        return false;
    }

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