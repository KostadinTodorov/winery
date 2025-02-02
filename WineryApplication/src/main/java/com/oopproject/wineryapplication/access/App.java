package com.oopproject.wineryapplication.access;
import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import com.oopproject.wineryapplication.access.daos.dao.EntityDao;
import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.Act;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
//        EntityFieldMap<Employee> addEntityController = new EntityFieldMap<Employee>(new Employee());

        TemplateDao<Act> templateDao = new TemplateDao<Act>(Act.class);
        templateDao.getAll();
//        templateDao.get()
        ActDao actDao = new ActDao();
//        actDao.get();
//        actDao.getAll();
//        actDao.add();
//        actDao.insert();
//        actDao.update();
//        actDao.delete()
        List<Integer> list = new ArrayList<>();
        list.toArray();
    }
}
