package com.oopproject.wineryapplication.data;

import com.oopproject.wineryapplication.access.daos.EmployeeDao;
import com.oopproject.wineryapplication.access.daos.PersonDao;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.access.entities.Person;

import java.util.ArrayList;
import java.util.List;

public class User
{
    // Static variable to hold the single instance of the class
    private static Employee instance;

    // Private constructor to prevent direct instantiation
    private User()
    {

    }


    // Public static method to provide access to the instance
    public static Employee GetInstance(String employeeName, String email, String password)
    {
        List<Employee> chosenEmployees = new EmployeeDao().getAll().stream().filter(
                e -> e.getPerson().getPersonName().equals(employeeName)
                        && e.getPerson().getEmail().equals(email)
        ).toList();
        Employee chosenEmployee = null;
        if (instance == null){
            if (chosenEmployees.size() == 1 && chosenEmployees.getFirst().getPassword().equals(password))
            {
                instance = chosenEmployees.getFirst();
            }
            else {
                //TODO: add relevant exceptions
                throw new RuntimeException();
            }
        }
        return instance;
    }

    public static Employee GetInstance(int id, String password)
    {
        Employee chosenEmployee = new EmployeeDao().get(id);
        if (instance == null){
            if (chosenEmployee != null)
            {
                if (chosenEmployee.getPassword().equals(password)) {
                    instance = chosenEmployee;
                }
            }
            else {
                //TODO: add relevant exceptions
                //TODO: add alert
                throw new RuntimeException();
            }
        }
        return instance;
    }

    public static Employee GetInstance()
    {
        return instance;
    }
}
