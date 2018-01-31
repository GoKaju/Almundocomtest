package com.gokaju.almundocomtest.controllers;

import com.gokaju.almundocomtest.objects.Call;
import com.gokaju.almundocomtest.objects.CallCenter;
import com.gokaju.almundocomtest.objects.Customer;
import com.gokaju.almundocomtest.objects.Employee;
import com.gokaju.almundocomtest.util.EmployeeType;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author @GoKaju
 * @version 1.0 28/01/2018
 */
public class Distpatcher {

    private final CallCenter callCenter;
    private final ExecutorService executor;
    private static Distpatcher distpatcher;

    /**
     * Constructor privado de la clase recive la cantidad maxima de canales a
     * utilizar .
     *
     * @param maxChanels
     */
    private Distpatcher(int maxChanels) {
        this.callCenter = new CallCenter();
        this.executor = Executors.newFixedThreadPool(maxChanels);
    }

    /**
     * @Singleton
     *
     * @param maxChanels
     * @return Distpatcher
     */
    public static Distpatcher getDistpatcher(int maxChanels) {
        if (distpatcher == null) {
            distpatcher = new Distpatcher(maxChanels);
        }
        return distpatcher;
    }

    public static Distpatcher getDistpatcher() {
        return distpatcher;
    }

    /**
     * Recibe la llamada y la envia a la cola del callCenter y luego invoca el
     * metodo assingCall.
     *
     * @param call
     */
    public void dispatchCall(Call call) {
        System.out.println("llego -->"+call);
        callCenter.addCall(call);
        assignCall();
    }

    public synchronized CallCenter getCallCenter() {
        return callCenter;
    }

    /**
     * Evalua si hay llamadas en cola y si hay empleados disponibles asigna este
     * a la llamada y se envia el executorService.
     */
    public synchronized void assignCall() {

        if (callCenter.availableCalls()) {
            Employee employee = callCenter.getAvaliableEmployee();
            if (employee != null) {
                Call c = callCenter.getCall();
                c.setCallReceive(employee);
                executor.submit(c);
            }
        }
    }

    /**
     * Envia orden de apagado al executorService y espera 10 seg si no lo cierra
     * a la fuerza.
     *
     * @throws java.lang.InterruptedException
     */
    public void close() throws InterruptedException {
        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
      public static void main(String[] args) {
        try {
            
            System.out.println("### start ####");
            Distpatcher distpdatcher = Distpatcher.getDistpatcher(10);
            CallCenter callCenter = Distpatcher.getDistpatcher().getCallCenter();
            callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e1"));
            callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e2"));
            callCenter.registerEmployee(new Employee(EmployeeType.SUPERVISOR,"e5"));
            callCenter.registerEmployee(new Employee(EmployeeType.SUPERVISOR,"e6"));
            callCenter.registerEmployee(new Employee(EmployeeType.DIRECTOR, "e7"));
            callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e3"));
            callCenter.registerEmployee(new Employee(EmployeeType.OPERADOR, "e4"));
            
            for (int i = 0; i < 100; i++) {
                Call c1 = new Call(new Customer("c" + i));
                distpdatcher.dispatchCall(c1); 
            }
        } catch ( SecurityException ex) {
            Logger.getLogger(Distpatcher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
