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
    public boolean add(Answer answer) {
        if (answer.getId() == null) {
            return insert(answer) != null;
        }
        else if (get(answer.getId()) == null) {
            return insert(answer) != null;
        }
        return false;
    }

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

    @Override
    public boolean update(int id, Answer answer) {
        if (get(id) != null) {
            answer.setId(id);
            return insert(answer) != null;
        }
        return false;
    }

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
