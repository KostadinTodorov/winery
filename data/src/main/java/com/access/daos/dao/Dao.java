package com.access.daos.dao;
import com.access.daos.dao.Dao;
import com.access.entities.*;
import com.access.entities.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

import java.util.List;

public interface Dao<T> {
    public T get(int id);
    public List<T> getAll();
    public boolean add(T t);
    public T insert(T t);
    public boolean update(int id,T t);
    public boolean delete(int id);
    public Session createSession() throws RuntimeException;
}
