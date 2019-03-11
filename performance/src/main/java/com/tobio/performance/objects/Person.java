package com.tobio.performance.objects;

public class Person {

    protected String  dni  = null;
    protected String  name = null;
    protected Integer personId;


    public Person(Integer personId) {
        this.personId = personId;
    }


    public String getDNI() {
        return dni;
    }


    public void setDNI(String dNI) {
        dni = dNI;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getPersonId() {
        return personId;
    }


    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
