package com.techdecode.antron_express.models;

public class User {
    String id;
    String name;
    String add;
    String CONTACTP;
    String ALT_PHONE;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setCONTACTP(String CONTACTP) {
        this.CONTACTP = CONTACTP;
    }

    public void setALT_PHONE(String ALT_PHONE) {
        this.ALT_PHONE = ALT_PHONE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdd() {
        return add;
    }

    public String getCONTACTP() {
        return CONTACTP;
    }

    public String getALT_PHONE() {
        return ALT_PHONE;
    }

    public User(String id, String name, String add, String CONTACTP, String ALT_PHONE) {
        this.id = id;
        this.name = name;
        this.add = add;
        this.CONTACTP = CONTACTP;
        this.ALT_PHONE = ALT_PHONE;
    }

    public String toString() {
        return name+' ' +id;
    }

}
