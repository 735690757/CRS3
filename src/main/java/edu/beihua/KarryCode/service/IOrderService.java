package edu.beihua.KarryCode.service;

import edu.beihua.KarryCode.entity.Customer;

public interface IOrderService {
    boolean rental(Customer customer);
    void selectOrder(Customer customer,int sel);
    boolean returnVehicle(Customer customer);
    boolean occupancyInformation();
}
