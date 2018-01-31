package com.gokaju.almundocomtest.controllers;

import com.gokaju.almundocomtest.objects.Call;
import com.gokaju.almundocomtest.objects.CallCenter;
import com.gokaju.almundocomtest.objects.Customer;
import com.gokaju.almundocomtest.objects.Employee;
import com.gokaju.almundocomtest.util.EmployeeType;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author @GoKaju
 * @version 1.0 30/01/2018
 */
public class DistpatcherTest {

    private Distpatcher distpatcher;

    public DistpatcherTest() {
    }

    @Before
    public void setUp() {
        distpatcher = Distpatcher.getDistpatcher(10);
        CallCenter callCenter = distpatcher.getCallCenter();
        // Regirtro 10 empleados de diferente tipo en diferente orden.
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e1"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e2"));
        callCenter.registerEmployee(new Employee(EmployeeType.SUPERVISOR, "e5"));
        callCenter.registerEmployee(new Employee(EmployeeType.SUPERVISOR, "e6"));
        callCenter.registerEmployee(new Employee(EmployeeType.DIRECTOR, "e7"));
        callCenter.registerEmployee(new Employee(EmployeeType.DIRECTOR, "e8"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e3"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e4"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e9"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e10"));
    }

    @After
    public void destroy() throws InterruptedException {
        distpatcher.close();
    }

    /**
     * Test de testDispatchCall. Se prueba que ya no hallan llamadas en cola,
     * puesto que debieron ser asignadas a 10 empleados tomando los 10 canales
     * disponibles.
     */
    @Test
    public void testDispatchCall() {
        System.out.println("testDispatchCall 10 llamadas simultaneas.");
        // envio 10 llamadas a dispatchCall
        for (int i = 0; i < 10; i++) {
            distpatcher.dispatchCall(new Call(new Customer("customer" + i)));
        }
        assertEquals(false, distpatcher.getCallCenter().availableCalls());

    }
    /**
     * Test de testDispatchCall. Se prueba que ya no hallan llamadas en cola,
     * puesto que debieron ser asignadas a 10 empleados tomando los 10 canales
     * disponibles.
     */
    @Test
    public void testDispatchCall2() {
        System.out.println("testDispatchCall 50 llamadas simultaneas.");
        // envio 50 llamadas a dispatchCall
        for (int i = 0; i < 50; i++) {
            distpatcher.dispatchCall(new Call(new Customer("customer" + i)));
        }
        //Al finalizar el for 
        assertEquals(false, distpatcher.getCallCenter().availableCalls());

    }

}
