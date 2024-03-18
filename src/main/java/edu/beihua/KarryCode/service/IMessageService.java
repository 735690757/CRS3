package edu.beihua.KarryCode.service;

import edu.beihua.KarryCode.entity.Admin;
import edu.beihua.KarryCode.entity.Customer;

public interface IMessageService {
    void showAllMessage();
    void showAllMessageAll(Admin admin);
    boolean somethingToSay(Customer customer);
    boolean commentControl(Admin admin);
}
