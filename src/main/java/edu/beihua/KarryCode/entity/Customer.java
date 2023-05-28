package edu.beihua.KarryCode.entity;

import java.io.Serializable;

public class Customer extends User{
    private String customer_Email;
    private String customer_Phone;
    private double customer_money;



    public Customer(int id, String name, String password, String customer_Email, String customer_Phone, double customer_money) {
        super(id, name, password);
        this.customer_Email = customer_Email;
        this.customer_Phone = customer_Phone;
        this.customer_money = customer_money;
    }

    public Customer( String name, String password,String customer_Email, String customer_Phone, double customer_money) {
        super(name, password);
        this.customer_Email = customer_Email;
        this.customer_Phone = customer_Phone;
        this.customer_money = customer_money;
    }
    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_Email='" + customer_Email + '\'' +
                ", customer_Phone='" + customer_Phone + '\'' +
                ", customer_money=" + customer_money +
                '}';
    }

    public String getCustomer_Email() {
        return customer_Email;
    }

    public void setCustomer_Email(String customer_Email) {
        this.customer_Email = customer_Email;
    }

    public String getCustomer_Phone() {
        return customer_Phone;
    }

    public void setCustomer_Phone(String customer_Phone) {
        this.customer_Phone = customer_Phone;
    }

    public double getCustomer_money() {
        return customer_money;
    }

    public void setCustomer_money(double customer_money) {
        this.customer_money = customer_money;
    }



}
