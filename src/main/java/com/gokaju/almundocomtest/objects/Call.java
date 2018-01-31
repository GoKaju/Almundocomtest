package com.gokaju.almundocomtest.objects;

import com.gokaju.almundocomtest.controllers.Distpatcher;
import com.gokaju.almundocomtest.util.Constants;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author @GoKaju
 * @version 1.0 28/01/2018
 */
public class Call implements Runnable {

    private final long callId;// Id de la llamada
    private long callDuration; // Duracion en segundos
    private final Customer callCustomer;// Cliente en la linea
    private Employee callReceive;// Empleado que procesa la llamada

    /**
     * Constructor que recibe el cliente de la llamada e instancia el id de
     * esta.
     *
     * @param callCustomer
     */
    public Call(Customer callCustomer) {
        this.callId = Constants.getNextCallId();
        this.callCustomer = callCustomer;
    }

    public long getCallid() {
        return callId;
    }

    public long getCallDuration() {
        return callDuration;
    }

    public Customer getCallCustomer() {
        return callCustomer;
    }

    public Employee getCallReceive() {
        return callReceive;
    }

    public void setCallReceive(Employee callReceive) {
        this.callReceive = callReceive;
    }

    @Override
    public String toString() {
        return "Call{" + "callId=" + callId + ", callDuration=" + callDuration
                + ", callCustomer=" + callCustomer
                + ", callReceive=" + callReceive + '}';
    }

    /**
     * Retorna un long aleatorio entre un rango.
     *
     * @param callMin
     * @param callMax
     * @return long
     */
    private long getRamdomDuration(long callMin, long callMax) {
        return ThreadLocalRandom.current().nextLong(callMin, callMax);
    }

    /**
     * Runnable, simula una llama durmiendo el hilo aleatoreamente entre 5 y 10
     * seg luego retorna el empleado a la cola y invoca el metodo assignCall de
     * Dispatcher para procesar las llamadas pendientes.
     *
     */
    @Override
    public void run() {
        try {
            Logger.getLogger(Call.class.getName()).log(Level.INFO, null,"Procesando -->"+toString() );
            this.callDuration = this.getRamdomDuration(5, 10);
            TimeUnit.SECONDS.sleep(callDuration);
            Distpatcher.getDistpatcher().getCallCenter()
                    .registerEmployee(callReceive);
            Distpatcher.getDistpatcher().assignCall();
            Logger.getLogger(Call.class.getName()).log(Level.INFO, null,"fin -->"+toString() );
        } catch (InterruptedException ex) {
            Logger.getLogger(Call.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
