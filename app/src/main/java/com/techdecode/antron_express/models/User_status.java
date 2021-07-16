package com.techdecode.antron_express.models;

public class User_status {
    String id;

    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User_status(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return name+' ' +id;
    }
}
