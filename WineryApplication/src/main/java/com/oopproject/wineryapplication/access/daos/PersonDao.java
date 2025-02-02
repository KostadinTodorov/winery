package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Person;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Person} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link Person}.</li>
 *   <li>Добавяне на нов обект {@link Person}.</li>
 *   <li>Актуализиране на съществуващ обект {@link Person}.</li>
 *   <li>Изтриване на обект {@link Person} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class PersonDao extends EntityDao<Person> {

    /**
     * Конструира {@code PersonDao} за извършване на CRUD операции върху обекта {@link Person}.
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