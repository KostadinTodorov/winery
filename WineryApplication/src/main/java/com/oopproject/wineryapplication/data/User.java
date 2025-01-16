package com.oopproject.wineryapplication.data;

import com.oopproject.wineryapplication.access.daos.EmployeeDao;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.helpers.SceneHelper;
import com.oopproject.wineryapplication.helpers.Scenes;
import javafx.scene.control.Alert;

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
    public static Employee GetInstance(String employeeName, String password)
    {
        List<Employee> chosenEmployees = new EmployeeDao().getAll().stream().filter(
                e -> e.getPerson().getPersonName().equals(employeeName)
                        && e.getPassword().equals(password)
        ).toList();

        if (instance == null){
            if (chosenEmployees.size() == 1 /*&& chosenEmployees.getFirst().getPassword().equals(password)*/){

                instance = chosenEmployees.getFirst();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Two users with the same Name and Password!");
                alert.setContentText("Please manage them!");
                alert.showAndWait();


                SceneHelper.switchTo(Scenes.WELLCOME);
            }
        }
        else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("The user singleton has already been instanced!");
            alert.showAndWait();
        }


        return instance;
    }

}
