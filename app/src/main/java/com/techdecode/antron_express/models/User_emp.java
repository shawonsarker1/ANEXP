package com.techdecode.antron_express.models;

public class User_emp {
    String name;
    String name2;

    public void setName(String name) {
        this.name = name;
    }

    public User_emp(String name, String name2) {
        this.name = name;
        this.name2 = name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }
    public String toString() {
        return name;
    }
}
