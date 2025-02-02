package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.Machine;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Machine} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link Machine}.</li>
 *   <li>Добавяне на нов обект {@link Machine}.</li>
 *   <li>Актуализиране на съществуващ обект {@link Machine}.</li>
 *   <li>Изтриване на обект {@link Machine} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class MachineDao extends EntityDao<Machine> {

    /**
     * Конструира {@code MachineDao} за извършване на CRUD операции върху обекта {@link Machine}.
     * {@inheritDoc}
     */
    public MachineDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Machine get(int id) {
        try (Session session = createSession()) {
            return session.get(Machine.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Machine> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Machine", Machine.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Machine machine) {
        if (machine.getId() == null) {
            return insert(machine) != null;
        } else if (get(machine.getId()) == null) {
            return insert(machine) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(int id, Machine machine) {
        if (get(id) != null) {
            machine.setId(id);
            return insert(machine) != null;
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