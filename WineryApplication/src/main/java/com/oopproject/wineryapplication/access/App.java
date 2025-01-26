package com.oopproject.wineryapplication.access;
import com.oopproject.wineryapplication.access.daos.dao.TemplateDao;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.entities.entity.Entity;

public class App
{
    public static void main( String[] args )
    {
//        EntityFieldMap<Employee> addEntityController = new EntityFieldMap<Employee>(new Employee());
        Entity emp = new Employee();
        emp.setId(1);
        TemplateDao<Entity> templateDao = new TemplateDao<>(Entity.class);
        boolean r = templateDao.add(emp);
        Entity e = templateDao.get(1);
    }
}
