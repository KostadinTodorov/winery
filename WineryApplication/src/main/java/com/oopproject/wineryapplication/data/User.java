package com.oopproject.wineryapplication.data;

import com.oopproject.wineryapplication.access.daos.EmployeeDao;
import com.oopproject.wineryapplication.access.entities.Employee;
import com.oopproject.wineryapplication.helpers.scenes.SceneHelper;
import com.oopproject.wineryapplication.helpers.scenes.Scenes;
import javafx.scene.control.Alert;

import java.util.List;

/**
 * Класът {@code User} предоставя Singleton реализация за аутентикация и управление на текущата потребителска сесия
 * в системата. Основната му цел е идентификация и управление на {@link Employee}, който е влязъл в системата.
 * Тази реализация включва lazy instantiation и методи за настройване и изчистване на обекта Singleton.
 */
public class User
{
    private static Employee instance;
    private static String employeeOccupationBasedOnWellcome;

    private User() {

    }

    /**
     * Проверява дали съществува служител въз основа на подадените потребителско име и парола.
     * Използва lazy instantiation за създаване на Singleton инстанция на {@link Employee}.
     *
     * Логиката на метода включва:
     * <ul>
     *     <li>Филтриране на всички служители според съвпадение на потребителско име и парола.</li>
     *     <li>Проверка дали служителят работи в съответния сектор {@code employeeOccupationBasedOnWellcome}.</li>
     *     <li>Показване на подходящо съобщение за грешка при неправилни или дублирани данни.</li>
     * </ul>
     *
     * @param employeeName Името на служителя, което се използва за идентификация {@code employeeName}.
     * @param password Паролата на служителя, използвана за автентикация {@code password}.
     * @return Връща {@link Employee}, ако намереният служител съответства на критериите и Singleton инстанцията е създадена успешно.
     *         Ако не е възможно да се намери или да се създаде валиден служител, методът показва подходящо предупреждение
     *         и предприема действия като {@link #userLogout()}.
     * @throws RuntimeException Ако вече е създадена инстанция на потребителя (Singleton) и се опитва повторно влизане.
     */
    // Lazy instantiation of a singleton (There are other types of singletons). This is the most common one.
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

                    userLogout();
                }
            }
            else if (chosenEmployees.size() > 1 ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Two users with the same Name and Password!");
                alert.setContentText("Please manage them!");
                alert.showAndWait();

                userLogout();
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
            alert.setTitle("The User singleton has already been instanced!");
            alert.setContentText("Could be due to incorrectly executed login steps.");
            alert.showAndWait();

            throw new RuntimeException();
        }


        return instance;
    }

    /**
     * Методът {@code setEmployeeOccupationBasedOnWelcome} задава длъжност на служителя,
     * използвайки подадения низ като стойност. Това действие е свързано с вътрешното поле
     * {@link User#employeeOccupationBasedOnWellcome}, отразяващо текущата роля на служителя.
     *
     * След настройката, се отпечатва съобщение в конзолата, което указва коя длъжност е зададена.
     *
     * @param employeeOccupationBasedOnWellcome Стойност от тип {@code String}, представляваща името на длъжността,
     * която ще бъде назначена на служителя. Стойността се записва във вътрешното поле
     * {@link User#employeeOccupationBasedOnWellcome}.
     */
    public static void setEmployeeOccupationBasedOnWelcome(String employeeOccupationBasedOnWellcome) {
        User.employeeOccupationBasedOnWellcome = employeeOccupationBasedOnWellcome;

        System.out.println(String.format("Occupation %s was set!", User.employeeOccupationBasedOnWellcome));

    }

    /**
     * Методът {@code getEmployeeOccupationBasedOnWellcome} връща текущата длъжност
     * на служителя, която е свързана с вътрешното поле {@link User#employeeOccupationBasedOnWellcome}.
     *
     * Този метод е полезен за извличане на информация за ролята на служителя,
     * която може да бъде използвана в различни контексти като оторизация
     * или персонализиране на интерфейса.
     *
     * @return Връща стойност от тип {@code String}, представляваща текущата длъжност
     * на служителя, съхранена в {@link User#employeeOccupationBasedOnWellcome}.
     */
    public static String getEmployeeOccupationBasedOnWellcome() {
        return employeeOccupationBasedOnWellcome;
    }

    /**
     * Методът {@code getEmployee} връща Singleton инстанцията на {@link Employee}, ако такава съществува.
     * Ако приложението не е инициализирало инстанция на {@link Employee} чрез lazy instantiation,
     * се хвърля {@link NullPointerException}.
     *
     * Методът е част от Singleton модел на проектиране, осигуряващ, че ще има
     * само една инстанция на {@link Employee} в цялото приложение.
     *
     * @return Връща уникалната инстанция на {@link Employee}, ако тя съществува.
     * @throws NullPointerException Ако инстанцията {@code instance} не е инициализирана.
     */
    public static Employee getEmployee() {
        if (instance == null){
            throw new NullPointerException();
        }
        else {
            return instance;
        }
    }

    /**
     * Методът {@code clearSingleton} служи за изчистване на Singleton инстанцията
     * и нулиране на свързани вътрешни полета в класа {@link User}.
     *
     * Използването на този метод е подходящо в случаи, когато е необходимо
     * да се прекрати текущата сесия на потребителя или да се инициализира
     * състоянието на класа за повторна употреба.
     *
     * Логиката включва:
     * <ul>
     *     <li>Задаване на стойност {@code null} на статичното поле {@link User#instance},
     *     което представлява Singleton инстанцията на класа.</li>
     *     <li>Нулиране на стойността на полето {@link User#employeeOccupationBasedOnWellcome} чрез празен низ {@code ""}.</li>
     * </ul>
     *
     * @invocation Този метод е най-често извикван в контексти, където е необходимо
     * да се прекрати потребителската сесия, например в метода {@link User#userLogout()}.
     */
    private static void clearSingleton() {
        instance = null;
        employeeOccupationBasedOnWellcome = "";
    }

    /**
     * Методът {@code userLogout} служи за прекратяване на текущата потребителска сесия
     * и връщане към началния екран на приложението.
     *
     * Логиката на метода включва:
     * <ul>
     *     <li>Извикване на {@link #clearSingleton()} за изчистване на Singleton инстанцията на {@link User}
     *     и нулиране на свързаните състояния на класа.</li>
     *     <li>Смяна на текущата сцена в приложението към началната {@link Scenes#WELLCOME}
     *     с помощта на {@link SceneHelper#switchTo(Scenes)}.</li>
     * </ul>
     *
     * Методът е полезен в контексти, където е необходимо потребителят да бъде изведен от системата,
     * като например при изтичане на сесия или след изрично искане за изход.
     */
    public static void userLogout(){
        clearSingleton();
        SceneHelper.switchTo(Scenes.WELLCOME);
    }


}
