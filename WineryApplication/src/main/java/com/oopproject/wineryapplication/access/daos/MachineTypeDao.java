package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.MachineType;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link MachineType} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link MachineType}.</li>
 *   <li>Добавяне на нов обект {@link MachineType}.</li>
 *   <li>Актуализиране на съществуващ обект {@link MachineType}.</li>
 *   <li>Изтриване на обект {@link MachineType} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class MachineTypeDao extends EntityDao<MachineType> {

    /**
     * Конструира {@code MachineTypeDao} за извършване на CRUD операции върху обекта {@link MachineType}.
     * {@inheritDoc}
     */
    public MachineTypeDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MachineType get(int id) {
        try (Session session = createSession()) {
            return session.get(MachineType.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MachineType> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from MachineType", MachineType.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(MachineType machineType) {
        if (machineType.getId() == null) {
            return insert(machineType) != null;
        } else if (get(machineType.getId()) == null) {
            return insert(machineType) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(int id, MachineType machineType) {
        if (get(id) != null) {
            machineType.setId(id);
            return insert(machineType) != null;
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