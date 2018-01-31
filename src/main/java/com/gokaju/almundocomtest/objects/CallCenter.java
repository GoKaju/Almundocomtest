package com.gokaju.almundocomtest.objects;

import com.gokaju.almundocomtest.util.EmployeeType;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author @GoKaju
 * @version 1.0 28/01/2018
 */
public class CallCenter {

    private final Queue<Call> callQueue;
    private final EnumMap<EmployeeType, Queue<Employee>> employeeMap;

    public CallCenter() {
        this.callQueue = new LinkedList();
        this.employeeMap = new EnumMap(EmployeeType.class);
    }

    /**
     * Envia el empleado a la cola de disponibles segun su tipo(EmployeeType).
     *
     * @param employee
     */
    public void registerEmployee(Employee employee) {
        if (!employeeMap.containsKey(employee.getType())) {
            employeeMap.put(employee.getType(), new LinkedList());
        }
        employeeMap.get(employee.getType()).add(employee);
    }

    /**
     * Devuelve el primer empleado disponible de la cola, respetando el
     * requerimiento de jerarquia.
     *
     * @return employee
     */
    public Employee getAvaliableEmployee() {
        for (Map.Entry<EmployeeType, Queue<Employee>> entry : employeeMap.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                return entry.getValue().poll();
            }
        }
        return null;
    }

    /**
     * Adiciona la llamada a la cola.
     *
     * @param call
     * @return Retorna boolean resultante al añadir la llamda a la cola
     */
    public boolean addCall(Call call) {
        return callQueue.offer(call);
    }

    /**
     * Retorna la primera llamada pendiente por procesar de la cola.
     *
     * @return Call
     */
    public Call getCall() {
        return callQueue.poll();
    }

    /**
     * Retorna true si hay llamadas en cola o false si no.
     *
     * @return boolean
     */
    public boolean availableCalls() {
        return !callQueue.isEmpty();
    }
}
