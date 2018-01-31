package com.gokaju.almundocomtest.objects;

/**
 * @author @GoKaju
 * @version 1.0 28/01/2018
 */
public class Customer extends GeneralPerson {

    public Customer(String personName) {
        super(personName);
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + super.getPersonName() + "'}'";
    }

}
