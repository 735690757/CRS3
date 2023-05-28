package edu.beihua.KarryCode.entity;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(int customer_id, String customer_name, String customer_password) {
        super(customer_id, customer_name, customer_password);
    }


}
