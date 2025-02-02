package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Client;
import com.oopproject.wineryapplication.access.entities.FaultCode;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link FaultCode} с помощта на Hibernate.
 * <p>
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * <ul>
 *   <li>Извличане на единични или множество обекти {@link FaultCode}.</li>
 *   <li>Добавяне на нов обект {@link FaultCode}.</li>
 *   <li>Актуализиране на съществуващ обект {@link FaultCode}.</li>
 *   <li>Изтриване на обект {@link FaultCode} по неговия ID.</li>
 * </ul>
 * <p>
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class FaultCodeDao extends EntityDao<FaultCode> {

    /**
     * Конструира {@code FaultCodeDao} за извършване на CRUD операции върху обекта {@link FaultCode}.
     * {@inheritDoc}
     */
    public FaultCodeDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FaultCode get(int id) {
        try (Session session = createSession()) {
            return session.get(FaultCode.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FaultCode> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from FaultCode", FaultCode.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(FaultCode faultCode) {
        if (faultCode.getId() == null) {
            return insert(faultCode) != null;
        } else if (get(faultCode.getId()) == null) {
            return insert(faultCode) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FaultCode insert(FaultCode faultCode) {
        FaultCode newFaultCode = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newFaultCode = session.merge(faultCode);
            try {
                transaction.commit();
                return newFaultCode;
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
    public boolean update(int id, FaultCode faultCode) {
        if (get(id) != null) {
            faultCode.setId(id);
            return insert(faultCode) != null;
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
                FaultCode faultCode = session.get(FaultCode.class, id);
                if (faultCode != null) {
                    session.remove(faultCode);
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