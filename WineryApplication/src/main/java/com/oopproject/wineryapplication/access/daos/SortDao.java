package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Sort;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SortDao extends EntityDao<Sort> {

    public SortDao() {
        super();
    }

    @Override
    public Sort get(int id) {
        try (Session session = createSession()) {
            return session.get(Sort.class, id);
        }
    }

    @Override
    public List<Sort> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Sort", Sort.class).list();
        }
    }

    @Override
    public boolean add(Sort sort) {
        if (sort.getId() == null) {
            return insert(sort) != null;
        } else if (get(sort.getId()) == null) {
            return insert(sort) != null;
        }
        return false;
    }

    @Override
    public Sort insert(Sort sort) {
        Sort newSort = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newSort = session.merge(sort);
            try {
                transaction.commit();
                return newSort;
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
    public boolean update(int id, Sort sort) {
        if (get(id) != null) {
            sort.setId(id);
            return insert(sort) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Sort sort = session.get(Sort.class, id);
                if (sort != null) {
                    session.remove(sort);
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