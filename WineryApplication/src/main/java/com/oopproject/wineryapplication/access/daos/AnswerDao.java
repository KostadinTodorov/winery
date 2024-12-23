package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.daos.dao.Dao;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import com.oopproject.wineryapplication.access.entities.Answer;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AnswerDao extends EntityDao<Answer> {
    public AnswerDao() {
        super();
    }

    @Override
    public Answer get(int id) {
        try (Session session = createSession()) {
            return session.get(Answer.class, id);
        }
    }

    @Override
    public List<Answer> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Answer", Answer.class).list();
        }
    }

    @Override
    public boolean add(Answer Answer) {
        if (Answer.getId() == null) {
            return insert(Answer) != null;
        }
        else if (get(Answer.getId()) == null) {
            return insert(Answer) != null;
        }
        return false;
    }

    @Override
    public Answer insert(Answer Answer) {
        Answer newAnswer = null;
        try(Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newAnswer = session.merge(Answer);
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

    @Override
    public boolean update(int id, Answer Answer) {
        if (get(id) != null) {
            Answer.setId(id);
            return insert(Answer) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Answer Answer = session.get(Answer.class, id);
                if (Answer != null) {
                    session.remove(Answer);
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
