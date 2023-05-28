package edu.beihua.KarryCode.service;

import edu.beihua.KarryCode.entity.Admin;

public interface IAdminService {
    Admin Login();
    void AdminView(Admin admin);
}
