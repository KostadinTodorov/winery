package com.oopproject.wineryapplication.access.entities.entity.contracts;

import com.oopproject.wineryapplication.access.daos.dao.Dao;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;

public interface Entitiable {
    Integer getId();

    void setId(Integer id);
    Dao getDao();
}
