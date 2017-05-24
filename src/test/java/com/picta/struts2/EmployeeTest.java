package com.picta.struts2;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void testEmployee() {
        Employee employee = new Employee("Pierre", "Dupond", 2000);

        assertEquals("Pierre", employee.getFirstName());
        assertEquals("Dupond", employee.getLastName());
        assertEquals(2000, employee.getSalary());

    }
}
