package edu.beihua.KarryCode.entity;

import java.io.Serializable;

public class Vehicle{
    private int Vehicle_id;
    private String Vehicle_license;
    private String Vehicle_name;
    private double Vehicle_rent;
    private boolean Vehicle_state;

    public Vehicle(int vehicle_id, String vehicle_license, String vehicle_name, double vehicle_rent, boolean vehicle_state) {
        Vehicle_id = vehicle_id;
        Vehicle_license = vehicle_license;
        Vehicle_name = vehicle_name;
        Vehicle_rent = vehicle_rent;
        Vehicle_state = vehicle_state;
    }

    public Vehicle() {
    }

    public int getVehicle_id() {
        return Vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        Vehicle_id = vehicle_id;
    }

    public String getVehicle_license() {
        return Vehicle_license;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "Vehicle_id=" + Vehicle_id +
                ", Vehicle_license='" + Vehicle_license + '\'' +
                ", Vehicle_name='" + Vehicle_name + '\'' +
                ", Vehicle_rent=" + Vehicle_rent +
                ", Vehicle_state=" + Vehicle_state +
                '}';
    }

    public void setVehicle_license(String vehicle_license) {
        Vehicle_license = vehicle_license;
    }

    public String getVehicle_name() {
        return Vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        Vehicle_name = vehicle_name;
    }

    public double getVehicle_rent() {
        return Vehicle_rent;
    }

    public void setVehicle_rent(double vehicle_rent) {
        Vehicle_rent = vehicle_rent;
    }

    public boolean isVehicle_state() {
        return Vehicle_state;
    }

    public void setVehicle_state(boolean vehicle_state) {
        Vehicle_state = vehicle_state;
    }
}
