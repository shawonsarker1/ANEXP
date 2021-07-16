package com.techdecode.antron_express.models;

public class User_district {
    String id;
    String name;

    public User_district(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String toString() {
        return name+' ' +id;
    }
}
