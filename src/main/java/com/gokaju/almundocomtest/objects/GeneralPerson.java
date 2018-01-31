package com.gokaju.almundocomtest.objects;

/**
 * @author @GoKaju
 * @version 1.0 28/01/2018
 */
public abstract class GeneralPerson {

    private String personName;

    public GeneralPerson(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

}
