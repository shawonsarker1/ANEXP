package com.techdecode.antron_express.models;

public class User_Way_bill {
    String bill;
    String adress;
    String city;
    String Contact_nam;
    String Contact_num;
    String weight;
    String ctn;
    String name;


    public String getBill() {
        return bill;
    }

    public String getAdress() {
        return adress;
    }

    public String getCity() {
        return city;
    }

    public String getContact_nam() {
        return Contact_nam;
    }

    public String getContact_num() {
        return Contact_num;
    }

    public String getWeight() {
        return weight;
    }

    public String getCtn() {
        return ctn;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContact_nam(String contact_nam) {
        Contact_nam = contact_nam;
    }

    public void setContact_num(String contact_num) {
        Contact_num = contact_num;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setCtn(String ctn) {
        this.ctn = ctn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User_Way_bill(String bill, String adress, String city, String contact_nam, String contact_num, String weight, String ctn, String name) {
        this.bill = bill;
        this.adress = adress;
        this.city = city;
        Contact_nam = contact_nam;
        Contact_num = contact_num;
        this.weight = weight;
        this.ctn = ctn;
        this.name=name;
    }

    public String toString() {
        return bill;
    }
}
