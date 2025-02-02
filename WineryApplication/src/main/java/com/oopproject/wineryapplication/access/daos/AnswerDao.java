package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.daos.dao.Dao;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import com.oopproject.wineryapplication.access.entities.Act;
import com.oopproject.wineryapplication.access.entities.Answer;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO клас за извършване на CRUD операции върху обекта {@link Answer} с помощта на Hibernate.
 *
 * Този клас разширява {@link EntityDao} и предоставя имплементации за:
 * - Извличане на единични или множество обекти {@link Answer}.
 * - Добавяне на нов обект {@link Answer}.
 * - Актуализиране на съществуващ обект {@link Answer}.
 * - Изтриване на обект {@link Answer} по неговия ID.
 *
 * Всеки метод използва Hibernate сесии за взаимодействия с базата данни и включва
 * подходяща обработка на транзакциите, за да се гарантира целостта на данните.
 */
public class AnswerDao extends EntityDao<Answer> {

    /**
     * Конструира {@code AnswerDao} за извършване на CRUD операции върху обекта {@link Answer}.
     * {@inheritDoc}
     */
    public AnswerDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Answer get(int id) {
        try (Session session = createSession()) {
            return session.get(Answer.class, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Answer> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Answer", Answer.class).list();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Answer answer) {
        if (answer.getId() == null) {
            return insert(answer) != null;
        }
        else if (get(answer.getId()) == null) {
            return insert(answer) != null;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Answer insert(Answer answer) {
        Answer newAnswer = null;
        try(Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newAnswer = session.merge(answer);
            try {
                transaction.commit();
                return newAnswer;
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
    public boolean update(int id, Answer answer) {
        if (get(id) != null) {
            answer.setId(id);
            return insert(answer) != null;
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
                Answer answer = session.get(Answer.class, id);
                if (answer != null) {
                    session.remove(answer);
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
