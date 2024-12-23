package com.oopproject.wineryapplication.access.daos.dao;

import org.hibernate.Session;

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
