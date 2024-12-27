package com.oopproject.wineryapplication.access;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.entities.Act;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.data.User;
import javafx.scene.control.Alert;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee emp;
        try {
//            emp = User.GetInstance("Joe Biden", "letsgobranden@mail.com", "46");
            emp = User.GetInstance(1,"46");
            System.out.println(emp.getPerson().getPersonName());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("Cause: " + e.getCause());
        }

    }
}
