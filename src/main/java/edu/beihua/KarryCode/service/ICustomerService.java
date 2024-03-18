package edu.beihua.KarryCode.service;

import edu.beihua.KarryCode.entity.Admin;
import edu.beihua.KarryCode.entity.Customer;

public interface ICustomerService {
    Customer Login();
    boolean Register();
    void howMuchMoneyDoIHave(Customer customer);
    boolean chargeOrRecharge(Customer customer,int num);
    void queryByCustomerName_fuzzy(Admin admin);
    void queryAllCustomer(Admin admin);
    boolean CustomerInformationModified(Admin admin);
    boolean forgotPassword();
    boolean forgotUsername();
}
