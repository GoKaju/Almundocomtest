package com.gokaju.almundocomtest.controllers;

import com.gokaju.almundocomtest.objects.Call;
import com.gokaju.almundocomtest.objects.CallCenter;
import com.gokaju.almundocomtest.objects.Customer;
import com.gokaju.almundocomtest.objects.Employee;
import com.gokaju.almundocomtest.util.EmployeeType;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author @GoKaju
 * @version 1.0 30/01/2018
 */
public class DistpatcherTest {

    private static Distpatcher distpatcher;

    public DistpatcherTest() {
    }

  @BeforeClass
  public static void setUpClass(){
     System.out.println("setUpClass");
        distpatcher = Distpatcher.getDistpatcher(10);
  }
    @Before
    public void setUp() {
        System.out.println("setUp");
        CallCenter callCenter = distpatcher.getCallCenter();
        // Registro 10 empleados de diferente tipo en diferente orden.
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


    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        System.out.println("tearDownClass");
        distpatcher.close();
    }
    /**
     * Test de testDispatchCall. Se prueba que ya no hallan llamadas en cola,
     * puesto que debieron ser asignadas a 10 empleados tomando los 10 canales
     * disponibles.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testDispatchCall() throws InterruptedException {
        System.out.println("testDispatchCall 10 llamadas simultaneas, 10 empleados.");
        // envio 10 llamadas a dispatchCall
        for (int i = 0; i < 10; i++) {
            distpatcher.dispatchCall(new Call(new Customer("customer" + i)));
        }
        // Se coloca un sleep al thread principal para evidenciar el proceso.
        TimeUnit.SECONDS.sleep(10);
        assertEquals(false, distpatcher.getCallCenter().availableCalls());

    }

    /**
     * Test de testDispatchCall. Se prueba con 10 canales, 10 empleados y 50
     * llamadas, se asignan 10 llamadas a la ves y el resto se asigna cada vez
     * que un empleado se desocupe.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testDispatchCall2() throws InterruptedException {
        System.out.println("testDispatchCall 50 llamadas simultaneas, 10 empleados.");
        System.out.println("Empleados disponibles -->" + distpatcher
                .getCallCenter().getAvaliableEmployeesSize());
        // envio 50 llamadas a dispatchCall
        for (int i = 0; i < 50; i++) {
            distpatcher.dispatchCall(new Call(new Customer("customer" + i)));
        }
        // Se coloca un sleep al thread principal para evidenciar el proceso.
        TimeUnit.SECONDS.sleep(40);
        assertEquals(false, distpatcher.getCallCenter().availableCalls());

    }

    /**
     * Test de testDispatchCall. Se prueba con 10 canales, 20 empleados y 50
     * llamadas, se asignan 20 llamadas a la ves, pero solo se procesan de a 10
     * y las otras 10 quedan en la cola de ejecucion del executorService y el
     * resto se asigna cada vez que un empleado se desocupe.
     *
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testDispatchCall3() throws InterruptedException {
        System.out.println("testDispatchCall 50 llamadas simultaneas, 20 empleados.");
         CallCenter callCenter = distpatcher.getCallCenter();
        // Registro 10 empleados mas de diferente tipo en diferente orden.
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e11"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e22"));
        callCenter.registerEmployee(new Employee(EmployeeType.SUPERVISOR, "e15"));
        callCenter.registerEmployee(new Employee(EmployeeType.SUPERVISOR, "e16"));
        callCenter.registerEmployee(new Employee(EmployeeType.DIRECTOR, "e17"));
        callCenter.registerEmployee(new Employee(EmployeeType.DIRECTOR, "e18"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e13"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e14"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e19"));
        callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e20"));
        System.out.println("Empleados disponibles -->" + distpatcher
                .getCallCenter().getAvaliableEmployeesSize());
        // envio 50 llamadas a dispatchCall
        for (int i = 0; i < 50; i++) {
            distpatcher.dispatchCall(new Call(new Customer("customer" + i)));
        }
        // Se coloca un sleep al thread principal para evidenciar el proceso.
        TimeUnit.SECONDS.sleep(40);
        assertEquals(false, distpatcher.getCallCenter().availableCalls());

    }

}
