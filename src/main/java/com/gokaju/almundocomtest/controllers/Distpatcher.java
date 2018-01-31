package com.gokaju.almundocomtest.controllers;

import com.gokaju.almundocomtest.objects.Call;
import com.gokaju.almundocomtest.objects.CallCenter;
import com.gokaju.almundocomtest.objects.Employee;
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
        Logger.getLogger(Call.class.getName()).log(Level.INFO, null,"init -->"+call);
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
}
