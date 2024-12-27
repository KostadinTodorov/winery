package com.oopproject.wineryapplication.access.daos;
import com.oopproject.wineryapplication.access.entities.${CAPNAME};
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ${CAPNAME}Dao extends EntityDao<${CAPNAME}> {

    public ${CAPNAME}Dao() {
        super();
    }

    @Override
    public ${CAPNAME} get(int id) {
        try (Session session = createSession()) {
            return session.get(${CAPNAME}.class, id);
        }
    }

    @Override
    public List<${CAPNAME}> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from ${CAPNAME}", ${CAPNAME}.class).list();
        }
    }

    @Override
    public boolean add(${CAPNAME} ${UNCAPNAME}) {
        if (${UNCAPNAME}.getId() == null) {
            return insert(${UNCAPNAME}) != null;
        }
        else if (get(${UNCAPNAME}.getId()) == null) {
            return insert(${UNCAPNAME}) != null;
        }
        return false;
    }

    @Override
    public ${CAPNAME} insert(${CAPNAME} ${UNCAPNAME}) {
        ${CAPNAME} new${CAPNAME} = null;
        try(Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            new${CAPNAME} = session.merge(${UNCAPNAME});
            try {
                transaction.commit();
                return new${CAPNAME};
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
    public boolean update(int id, ${CAPNAME} ${UNCAPNAME}) {
        if (get(id) != null) {
            ${UNCAPNAME}.setId(id);
            return insert(${UNCAPNAME}) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                ${CAPNAME} ${UNCAPNAME} = session.get(${CAPNAME}.class, id);
                if (${UNCAPNAME} != null) {
                    session.remove(${UNCAPNAME});
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