package com.gokaju.almundocomtest.objects;

import com.gokaju.almundocomtest.util.EmployeeType;

/**
 * @author @GoKaju
 * @version 1.0 28/01/2018
 */
public class Employee extends GeneralPerson {

    private final EmployeeType type;

    public Employee(EmployeeType type, String name) {
        super(name);
        this.type = type;
    }

    public EmployeeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Employee{name=" + super.getPersonName() + ", type=" + type
                + "}'";
    }

}
