package com.oopproject.wineryapplication.access.daos;

import com.oopproject.wineryapplication.access.entities.Company;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import jakarta.persistence.RollbackException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompanyDao extends EntityDao<Company> {

    public CompanyDao() {
        super();
    }

    @Override
    public Company get(int id) {
        try (Session session = createSession()) {
            return session.get(Company.class, id);
        }
    }

    @Override
    public List<Company> getAll() {
        try (Session session = createSession()) {
            return session.createQuery("from Company", Company.class).list();
        }
    }

    @Override
    public boolean add(Company company) {
        if (company.getId() == null) {
            return insert(company) != null;
        } else if (get(company.getId()) == null) {
            return insert(company) != null;
        }
        return false;
    }

    @Override
    public Company insert(Company company) {
        Company newCompany = null;
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            newCompany = session.merge(company);
            try {
                transaction.commit();
                return newCompany;
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
    public boolean update(int id, Company company) {
        if (get(id) != null) {
            company.setId(id);
            return insert(company) != null;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Session session = createSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Company company = session.get(Company.class, id);
                if (company != null) {
                    session.remove(company);
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