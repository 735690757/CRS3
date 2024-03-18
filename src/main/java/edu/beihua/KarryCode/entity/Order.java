package edu.beihua.KarryCode.entity;

import java.io.Serializable;

public class Order {
    private int order_id;

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", customer_name='" + customer_name + '\'' +
                ", Vehicle_license='" + Vehicle_license + '\'' +
                ", order_borrowdate='" + order_borrowdate + '\'' +
                ", order_returndate='" + order_returndate + '\'' +
                ", order_fee=" + order_fee +
                ", order_return=" + order_return +
                '}';
    }

    private String customer_name;
    private String Vehicle_license;
    private String order_borrowdate;
    private String order_returndate;
    private double order_fee;
    private boolean order_return;
    private Customer customer;
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Order(int order_id, String customer_name, String vehicle_license, String order_borrowdate, String order_returndate, double order_fee, boolean order_return, Customer customer) {
        this.order_id = order_id;
        this.customer_name = customer_name;
        Vehicle_license = vehicle_license;
        this.order_borrowdate = order_borrowdate;
        this.order_returndate = order_returndate;
        this.order_fee = order_fee;
        this.order_return = order_return;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order(int order_id, String customer_name, String vehicle_license, String order_borrowdate, String order_returndate, double order_fee, boolean order_return) {
        this.order_id = order_id;
        this.customer_name = customer_name;
        Vehicle_license = vehicle_license;
        this.order_borrowdate = order_borrowdate;
        this.order_returndate = order_returndate;
        this.order_fee = order_fee;
        this.order_return = order_return;
    }

    public Order() {

    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getVehicle_license() {
        return Vehicle_license;
    }

    public void setVehicle_license(String vehicle_license) {
        Vehicle_license = vehicle_license;
    }

    public String getOrder_borrowdate() {
        return order_borrowdate;
    }

    public void setOrder_borrowdate(String order_borrowdate) {
        this.order_borrowdate = order_borrowdate;
    }

    public String getOrder_returndate() {
        return order_returndate;
    }

    public void setOrder_returndate(String order_returndate) {
        this.order_returndate = order_returndate;
    }

    public double getOrder_fee() {
        return order_fee;
    }

    public void setOrder_fee(double order_fee) {
        this.order_fee = order_fee;
    }

    public boolean isOrder_return() {
        return order_return;
    }

    public void setOrder_return(boolean order_return) {
        this.order_return = order_return;
    }
}
