package com.oopproject.wineryapplication.data;

import com.oopproject.wineryapplication.access.daos.EmployeeDao;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.helpers.SceneHelper;
import com.oopproject.wineryapplication.helpers.Scenes;
import javafx.scene.control.Alert;

import java.util.List;

public class User
{
    private static Employee instance;
    private static String employeeOccupationBasedOnWellcome;

    private User() {

    }

    public static Employee CheckEmployee(String employeeName, String password) {

        List<Employee> chosenEmployees = new EmployeeDao().getAll().stream().filter(
                e -> (    e.getPerson().getPersonName().equals(employeeName)
                                && e.getPassword().equals(password))
        ).toList();

        if (instance == null){
            if (chosenEmployees.size() == 1 /*&& chosenEmployees.getFirst().getPassword().equals(password)*/){

                if(chosenEmployees.getFirst().getOccupation().getOccupation().equals(employeeOccupationBasedOnWellcome)){
                    instance = chosenEmployees.getFirst();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Wrong sector!");
                    alert.setContentText(String.format("Hello, %s!\nYou exist as %s user,\nbut you try to log in the %s segment.", employeeName, chosenEmployees.getFirst().getOccupation().getOccupation(), employeeOccupationBasedOnWellcome));
                    alert.showAndWait();

                    clearSingleton();
                    SceneHelper.switchTo(Scenes.WELLCOME);
                }
            }
            else if (chosenEmployees.size() > 1 ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Two users with the same Name and Password!");
                alert.setContentText("Please manage them!");
                alert.showAndWait();

                clearSingleton();
                SceneHelper.switchTo(Scenes.WELLCOME);
            }
            else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Wrong credentials!");
                alert.setContentText("Make sure you have entered the correct credentials!");
                alert.showAndWait();

                SceneHelper.switchTo(Scenes.LOG);
            }
        }
        else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("The user singleton has already been instanced!");
            alert.showAndWait();

        }


        return instance;
    }

    public static void setEmployeeOccupationBasedOnWelcome(String employeeOccupationBasedOnWellcome) {
        User.employeeOccupationBasedOnWellcome = employeeOccupationBasedOnWellcome;
    }

    public static String getEmployeeOccupationBasedOnWellcome() {
        return employeeOccupationBasedOnWellcome;
    }

    private static void clearSingleton() {
        instance = null;
        employeeOccupationBasedOnWellcome = "";
    }

    public static void userLogout(){
        clearSingleton();
        SceneHelper.switchTo(Scenes.WELLCOME);
    }


}
