package edu.beihua.KarryCode.test;

import edu.beihua.KarryCode.service.Impl.OrderServiceImpl;

public class test_for_occupancy {
    public static void main(String[] args) {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.occupancyInformation();
    }
}
