package com.gokaju.almundocomtest.objects;

import com.gokaju.almundocomtest.util.EmployeeType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author @GoKaju
 * @version 1.0 30/01/2018
 */
public class CallCenterTest {

    private CallCenter instance;

    @Before
    public void setUp() {
        instance = new CallCenter();
    }

    /**
     * Test de registerEmployee. Se prueba que el empleado que se ingresa a la
     * cola, sea el mismo que se obtiene de getAvaliableEmployee.
     */
    @Test
    public void testRegisterEmployee() {
        System.out.println("registerEmployee");
        Employee employee = new Employee(EmployeeType.OPERADOR, "e1");
        instance.registerEmployee(employee);
        assertEquals(employee, instance.getAvaliableEmployee());
    }

    /**
     * Test de getAvaliableEmployee. Se prueba que que el metodo devuelva los
     * empleados disponibles segun la jerarquia especificada.
     */
    @Test
    public void testGetAvaliableEmployee() {
        System.out.println("getAvaliableEmployee");
        // Registro de 10 empleados de diferente tipo en diferente orden.
        instance.registerEmployee(new Employee(EmployeeType.OPERADOR, "e1"));
        instance.registerEmployee(new Employee(EmployeeType.SUPERVISOR, "e5"));
        instance.registerEmployee(new Employee(EmployeeType.OPERADOR, "e2"));
        instance.registerEmployee(new Employee(EmployeeType.SUPERVISOR, "e6"));
        instance.registerEmployee(new Employee(EmployeeType.SUPERVISOR, "e7"));
        instance.registerEmployee(new Employee(EmployeeType.OPERADOR, "e3"));
        instance.registerEmployee(new Employee(EmployeeType.OPERADOR, "e4"));
        instance.registerEmployee(new Employee(EmployeeType.DIRECTOR, "e8"));
        instance.registerEmployee(new Employee(EmployeeType.OPERADOR, "e9"));
        instance.registerEmployee(new Employee(EmployeeType.OPERADOR, "e10"));
        // deben ser devueltos en orden jerarquico{OPERADOR(6),SUPERVISOR(3),DIRECTOR(1)}.
        //OPERADOR(6)
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.OPERADOR);
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.OPERADOR);
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.OPERADOR);
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.OPERADOR);
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.OPERADOR);
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.OPERADOR);
        //SUPERVISOR(3)
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.SUPERVISOR);
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.SUPERVISOR);
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.SUPERVISOR);
        //DIRECTOR(1)
        assertEquals(instance.getAvaliableEmployee().getType(), EmployeeType.DIRECTOR);

    }

    /**
     * Test de addCall. Se prueba que la instancia ingrese correctamente.
     */
    @Test
    public void testAddCall() {
        System.out.println("addCall");
        Call call = new Call(new Customer("c1"));
        boolean expResult = true;
        boolean result = instance.addCall(call);
        assertEquals(expResult, result);
    }

    /**
     * Test de getCall. Se prueba que se obtengan las llamadas en el orden
     * insertado.
     */
    @Test
    public void testGetCall() {
        System.out.println("getCall");
        Call call1 = new Call(new Customer("c1"));
        Call call2 = new Call(new Customer("c2"));
        Call call3 = new Call(new Customer("c3"));
        instance.addCall(call1);
        instance.addCall(call2);
        instance.addCall(call3);
        assertEquals(call1, instance.getCall());
        assertEquals(call2, instance.getCall());
        assertEquals(call3, instance.getCall());
    }

    /**
     * Test de availableCalls. Se prueba el retorno en caso de haber y no
     * llamadas.
     */
    @Test
    public void testAvailableCalls() {
        System.out.println("availableCalls");
        assertEquals(false, instance.availableCalls());
         instance.addCall(new Call(new Customer("c1")));
         assertEquals(true, instance.availableCalls());
    }

}
