package com.oopproject.wineryapplication.data;

import com.oopproject.wineryapplication.access.entities.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void checkEmployee() {
        setEmployeeOccupationBasedOnWelcome();
        Employee emp = User.CheckEmployee("P", "1");
        assertNotNull(emp);
    }

    @Test
    void setEmployeeOccupationBasedOnWelcome() {
        User.setEmployeeOccupationBasedOnWelcome("ceo");
    }

    @Test
    void getEmployeeOccupationBasedOnWellcome() {
        String str = User.getEmployeeOccupationBasedOnWellcome();
        assertEquals("ceo", str);
    }

    @Test
    void getEmployee() {
        assertNotNull(User.getEmployee());
    }

    @Test
    void userLogout() {
    }
}